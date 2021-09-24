package com.example.majiang;

import com.example.majiang.baopai.BaoPaiHandler;
import com.example.majiang.baopai.DefaultBaoPaiHandler;
import com.example.majiang.feng.DefaultTableInfoHandler;
import com.example.majiang.feng.MajTableInfo;
import com.example.majiang.feng.TableInfoHandler;
import com.example.majiang.p.Player;
import com.example.majiang.point.CalPointHandler;
import com.example.majiang.point.DefaultCalPointHandler;
import com.example.majiang.valid.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@Slf4j
public class MajGame {
    private MajTable table;
    public static Map<String, List<HuRecord>> record = new HashMap<>();
    public static Map<String, List<HuMaj>> playerRec = new HashMap<>();
    public static HuRecord max;
    public static AtomicInteger gameNum = new AtomicInteger(0);
    private Map<String, GameTmp> tmpInfos;
    /**
     * playerNo 为dealer 的人就是庄家 也就是东
     */
    private int dealer = 0;
    private int changFen = 0;
    private Hus hus;
    private CalPointHandler pointHandler;
    private TableInfoHandler tableInfoHandler;
    private BaoPaiHandler baoPaiHandler;
    private HuValidHandler huValidHandler;
    private GangValidHandler getMajHandMingGangValidHandler;
    private GangValidHandler showMingGangValidHandler;
    private GangValidHandler anGangValidHandler;
    private PengValidHandler pengValidHandler;
    private ChiValidHandler chiValidHandler;

    public MajGame(Hus hus) {
        this.hus = hus;
        huValidHandler = new DefaultHuValidHandler(hus);
        pointHandler = new DefaultCalPointHandler();
        tableInfoHandler = new DefaultTableInfoHandler();
        baoPaiHandler = new DefaultBaoPaiHandler();
        getMajHandMingGangValidHandler = new GetMajHandMingGangValidHandler();
        showMingGangValidHandler = new ShowMingGangValidHandler();
        anGangValidHandler = new AnGangValidHandler();
        pengValidHandler = new DefaultPengValidHandler();
        chiValidHandler = new DefaultChiValidHandler();
    }

    public void clear() {
        table = null;
    }

    private void initTmpInfos(List<Player<Maj, MajGroup>> players) {
        tmpInfos = new HashMap<>(players.size());
        for (int i = 0; i < players.size(); i++) {
            tmpInfos.put(players.get(i).getName(), new GameTmp(i, new ArrayList<>()));
        }
    }

    private void changeDealer(List<Integer> winnerNo, List<Player<Maj, MajGroup>> players) {
        MajTableInfo tableInfo = tableInfoHandler.changeDealer(winnerNo, dealer, changFen, players.size());
        this.dealer = tableInfo.getDealer();
        this.changFen = tableInfo.getChangFeng();
    }

    /**
     * 开始前的准备工作
     *
     * @param players
     */
    private void prePlay(List<Player<Maj, MajGroup>> players) {
        gameNum.incrementAndGet();
        initTmpInfos(players);
        table = new MajTable();
        table.shuffle();
        table.putMajs();
        table.addPlayers(players);
        table.deal();
        baoPaiHandler.init(table);
        /**
         * 放入开局摸的宝牌
         */
        baoPaiHandler.putBaoPai(baoPaiHandler.touchOpeningBaoPai());
    }

    private int gangNum = 0;
    private List<Integer> gangPlayerNos;

    private void initGangs() {
        gangNum = 0;
        gangPlayerNos = new ArrayList<>();
    }

    private boolean addGang(Integer no) {
        gangNum++;
        gangPlayerNos.add(no);
        if (gangNum <= 4 && gangPlayerNos.size() >= 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 功能实现了，但是代码还是不过优雅。
     *
     * @param players
     */
    public void play(List<Player<Maj, MajGroup>> players) {
        try {
            prePlay(players);
            int no = dealer;
            Maj currentMaj;
            int roundNum = 0;
            List<Integer> winnerNo = new ArrayList<>();
            boolean over = false;
            Player<Maj, MajGroup> player;
            boolean touchGang = false;
            boolean mingGang = false;
            initGangs();
            loopTouch:
            while (table.canTouch() && !over) {
                roundNum++;
                // 当前获取player
                player = players.get(no % players.size());
                //摸牌
                if (touchGang) {
                    currentMaj = table.touchGang();
                } else {
                    currentMaj = table.touch();
                }
                player.touch(currentMaj);
                //判断有没有自摸
                /**
                 *摸牌时的game info
                 */
                GameInfo touchGameInfo = buildGameInfo(players, player, currentMaj, true);
                HuRecord zimohuRecord = validFan(player, touchGameInfo);
                if (zimohuRecord != null && zimohuRecord.isHu()) {
                    if (player.chooseHu(zimohuRecord, touchGameInfo)) {
                        log.info("第{}局,场风{}庄家{},宝牌{},{}手,#{}# 自摸胡=>{},番数:{}", gameNum.get(), Maj.zi[changFen], players.get(dealer).getName(), baoPaiHandler.getBaoPais(), roundNum, player.getName(), zimohuRecord.getHuMaj(), buildFanString(zimohuRecord.getFans()));
                        //算自摸分
                        pointHandler.checkZiMoPoint(player, players, zimohuRecord);
                        winnerNo.add(tmpInfos.get(player.getName()).getPlayerNo());
                        //记录
                        rec(player, zimohuRecord);
                        /**
                         * 自摸不存在一炮多响
                         */
                        break;
                    }
                }
                List<GangRecord> gangs = validGang(player, touchGameInfo).stream().filter(GangRecord::isGang).collect(Collectors.toList());
                if (gangs.size() > 0) {
                    mingGang = gangs.get(0).isMingGang();
                    FuluObj fuluObj = player.chooseGang(gangs, touchGameInfo);
                    if (fuluObj != null) {
                        /**
                         * 杠不会有多种选择
                         */
                        player.addShow(fuluObj.getGroup(), fuluObj.getNeedRemoveHand(), fuluObj.getNeedRemoveShow());
                        /**
                         * 暗杠直接开一张宝牌
                         */
                        if (!mingGang) {
                            baoPaiHandler.putBaoPai(baoPaiHandler.touchGangBaoPai());
                        }
                        touchGang = true;
                        if (!addGang(tmpInfos.get(player.getName()).getPlayerNo())) {
                            return;
                        }
                        continue loopTouch;
                    }
                }
                currentMaj = player.play(buildGameInfo(players, player, null, false));
                /**
                 * 明杠在摸完杠牌再弃牌后才会摸宝牌
                 */
                if (touchGang) {
                    touchGang = false;
                    if (mingGang) {
                        baoPaiHandler.putBaoPai(baoPaiHandler.touchGangBaoPai());
                    }
                }
                boolean flag = true;
                loopCPG:
                while (flag) {
                    player = players.get(no % players.size());
                    //弃牌
                    for (int i = 1; i < players.size(); i++) {
                        Player<Maj, MajGroup> other = players.get((no + i) % players.size());
                        //判断没有没有放炮
                        HuRecord fangpaohuRecord = validFan(other, currentMaj, buildGameInfo(players, other, currentMaj, false));
                        if (fangpaohuRecord != null && fangpaohuRecord.isHu()) {
                            if (other.chooseHu(fangpaohuRecord, buildGameInfo(players, other, currentMaj, false))) {
                                log.info("第{}局,场风{}庄家{},宝牌{},{}手,#{}# 胡 #{}# 放炮=>{},番数:{}", gameNum.get(), Maj.zi[changFen], players.get(dealer).getName(), baoPaiHandler.getBaoPais(), roundNum, other.getName(), player.getName(), fangpaohuRecord.getHuMaj(), buildFanString(fangpaohuRecord.getFans()));
                                //算放炮分
                                pointHandler.checkFangPaoPoint(other, player, fangpaohuRecord);
                                //记录
                                rec(other, fangpaohuRecord);
                                winnerNo.add(tmpInfos.get(other.getName()).getPlayerNo());
                                /**
                                 * 可能一炮多响，要把其他玩家都判断了。
                                 */
                                over = true;
                                break;
                            }
                        }
                    }
                    /**
                     * 明杠
                     */
                    for (int i = 1; i < players.size(); i++) {
                        int cno = (no + i) % players.size();
                        Player<Maj, MajGroup> other = players.get(cno);
                        //判断没有没有放炮
                        List<GangRecord> mingGangRecords = validMingGang(other, buildGameInfo(players, other, currentMaj, false));
                        if (mingGangRecords.size() > 0) {
                            mingGang = mingGangRecords.get(0).isMingGang();
                            GangRecord mingGangRecord = mingGangRecords.get(0);
                            if (mingGangRecord.isGang()) {
                                /**
                                 * 碰不会有多种选择
                                 */
                                FuluObj fuluObj = other.chooseGang(Arrays.asList(mingGangRecord), buildGameInfo(players, other, currentMaj, false));
                                if (fuluObj != null) {
                                    other.addShow(fuluObj.getGroup(), fuluObj.getNeedRemoveHand());
                                    /**
                                     * 杠完摸一张牌，并且开一张宝牌
                                     */
                                    baoPaiHandler.putBaoPai(baoPaiHandler.touchGangBaoPai());
                                    /**
                                     * 不存在多个人都杠，所以一个人杠完就可以break
                                     */
                                    no = cno;
                                    touchGang = true;
                                    if (!addGang(tmpInfos.get(player.getName()).getPlayerNo())) {
                                        return;
                                    }
                                    continue loopTouch;
                                }

                            }
                        }
                    }
                    /**
                     * 碰
                     */
                    for (int i = 1; i < players.size(); i++) {
                        /**
                         * 按顺序
                         */
                        int cno = (no + i) % players.size();
                        Player<Maj, MajGroup> other = players.get(cno);
                        //判断没有没有放炮
                        PengRecord pengRecord = validPeng(other, buildGameInfo(players, other, currentMaj, false));
                        if (pengRecord != null && pengRecord.isPeng()) {
                            FuluObj fuluObj = other.choosePeng(pengRecord, buildGameInfo(players, other, currentMaj, false));
                            if (fuluObj != null) {
                                other.addShow(fuluObj.getGroup(), fuluObj.getNeedRemoveHand());
                                currentMaj = other.play(buildGameInfo(players, other, currentMaj, false));
                                no = cno;
                                continue loopCPG;
                            }
                        }
                    }
                    /**
                     * 下一家 判断吃
                     */

                    int cno = (no + 1) % players.size();
                    Player<Maj, MajGroup> nextPlayer = players.get(cno);
                    //判断没有没有放炮
                    ChiRecord chiRecord = validChi(nextPlayer, buildGameInfo(players, nextPlayer, currentMaj, false));
                    if (chiRecord != null && chiRecord.isChi()) {
                        FuluObj fuluObj = nextPlayer.chooseChi(chiRecord, buildGameInfo(players, nextPlayer, currentMaj, false));
                        if (fuluObj != null) {
                            nextPlayer.addShow(fuluObj.getGroup(), fuluObj.getNeedRemoveHand());
                            currentMaj = nextPlayer.play(buildGameInfo(players, nextPlayer, currentMaj, false));
                            no = cno;
                            continue loopCPG;
                            /**
                             * 可能一炮多响，要把其他玩家都判断了。
                             */
//                                discard = false;
                        }

                    }
                    flag = false;
                }
                /**
                 * 如果没有被胡吃碰杠，就进入弃牌堆
                 */
                players.get(no).addDiscard(currentMaj);
                //这一手结束，加1
                no = (++no >= players.size()) ? no % players.size() : no;
            }//这把打完，判断是否换庄
            changeDealer(winnerNo, players);
        } finally {
            //所有player清空牌
            for (Player<Maj, MajGroup> player : players) {
                player.over();
            }
        }
    }

    private ChiRecord validChi(Player<Maj, MajGroup> player, GameInfo gameInfo) {
        List<Maj> hand = player.getHand();
        return chiValidHandler.validChi(hand, gameInfo);
    }

    private PengRecord validPeng(Player<Maj, MajGroup> player, GameInfo gameInfo) {
        List<Maj> hand = player.getHand();
        return pengValidHandler.validPeng(hand, gameInfo);
    }

    private List<GangRecord> validGang(Player<Maj, MajGroup> player, GameInfo touchGameInfo) {
        List<GangRecord> res = new ArrayList<>();
        res.add(anGangValidHandler.validGang(player, touchGameInfo));
        res.add(showMingGangValidHandler.validGang(player, touchGameInfo));
        return res;
    }

    private List<GangRecord> validMingGang(Player<Maj, MajGroup> player, GameInfo touchGameInfo) {
        return Arrays.asList(getMajHandMingGangValidHandler.validGang(player, touchGameInfo));
    }

    //-----------------------------------------------------------------------------------------------------------------------------------//

    private GameInfo buildGameInfo(List<Player<Maj, MajGroup>> all, Player<Maj, MajGroup> currentPlayer, Maj currentMaj, boolean zimo) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setBaoPai(baoPaiHandler.getBaoPais());
        gameInfo.setChangFeng(changFen);
        gameInfo.setZiFeng(calZiFeng(currentPlayer));
        gameInfo.setPlayerTableInfos(buildPlayerTableInfoMap(all, currentPlayer));
        gameInfo.setZimo(zimo);
        gameInfo.setCurrentMaj(currentMaj);
        return gameInfo;
    }

    private Map<Integer, PlayerTableInfo> buildPlayerTableInfoMap(List<Player<Maj, MajGroup>> all, Player<Maj, MajGroup> player) {
        Map<Integer, PlayerTableInfo> res = new LinkedHashMap<>();
        for (Player<Maj, MajGroup> p : all) {
            if (p != player) {
                GameTmp gameTmp = tmpInfos.get(p.getName());
                res.put(gameTmp.getPlayerNo(), buildPlayerTableInfo(p));
            }
        }
        return res;
    }

    private PlayerTableInfo buildPlayerTableInfo(Player<Maj, MajGroup> player) {
        return new PlayerTableInfo(player.getShow(), player.getDiscard());
    }
    //----------------------------------------------------------------------------------------------------------------------------------//


    private String buildFanString(List<Fan> fans) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = buildMap(fans);
        if (fans.size() > 0) {
            if (fans.get(0).isYiMan()) {
                sb.append(sumFan(fans)).append("倍役满");
            } else {
                sb.append("共").append(sumFan(fans)).append("番");
            }
            sb.append(",");
            for (Fan fan : fans) {
                int prefixNum = 1;
                String type = fan.getType();
                Integer num = map.get(type);
                if (num > 1) {
                    prefixNum *= num;
                }
                if (fan.isSingle()) {
                    prefixNum *= (fan.getNum());
                }
                if (prefixNum > 1) {
                    sb.append(prefixNum);
                }
                sb.append(fan.getType());
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> buildMap(List<Fan> list) {
        Map<String, Integer> map = new HashMap<>(list.size());
        for (Fan f : list) {
            String type = f.getType();
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        return map;
    }

    private int sumFan(List<Fan> list) {
        int sum = 0;
        boolean isYiMan = false;
        for (Fan fan : list) {
            if (fan.isYiMan()) {
                if (!isYiMan) {
                    sum = 0;
                }
                isYiMan = true;
            }
            if ((isYiMan && fan.isYiMan()) || !isYiMan) {
                sum += fan.getNum();
            }
        }
        return sum;
    }
    //----------------------------------------------------------------------------------------------------------------------------------//


    private int calZiFeng(Player<Maj, MajGroup> player) {
        GameTmp gameTmp = tmpInfos.get(player.getName());
        if (gameTmp == null) {
            throw new NullPointerException();
        }
        return tableInfoHandler.calZiFeng(gameTmp.getPlayerNo(), dealer);
    }

    private void record(GameSettleAccount settleAccount) {

    }

    private void rec(Player<Maj, MajGroup> winner, HuRecord huRecord) {
        for (Fan fan : huRecord.getFans()) {
            List<HuRecord> list = record.getOrDefault(fan.getType(), new ArrayList<>());
            list.add(huRecord);
            record.put(fan.getType(), list);
            List<HuMaj> playerRecList = playerRec.getOrDefault(winner.getName(), new ArrayList<>());
            playerRecList.add(huRecord.getHuMaj());
            playerRec.put(winner.getName(), playerRecList);

            if (max == null) {
                max = huRecord;
            } else {
                List<Fan> fans = huRecord.getFans();
                int sumFan = sumFan(fans);
                if (huRecord.isYiMan()) {
                    if (max.isYiMan()) {
                        if (sumFan > sumFan(max.getFans())) {
                            max = huRecord;
                        }
                    } else {
                        max = huRecord;
                    }
                } else {
                    if (!max.isYiMan()) {
                        if (sumFan > sumFan(max.getFans())) {
                            max = huRecord;
                        }
                    }
                }
            }


        }
    }

    /**
     * 自摸
     *
     * @param player
     * @return
     */
    private HuRecord validFan(Player<Maj, MajGroup> player, GameInfo gameInfo) {
        return validFan(player.getHand(), player.getShow(), player.getDiscard(), gameInfo);
    }

    /**
     * 自摸
     *
     * @param player
     * @return
     */
    private HuRecord validFan(Player<Maj, MajGroup> player, Maj current, GameInfo gameInfo) {
        List<Maj> list = new ArrayList<>();
        list.addAll(player.getHand());
        list.add(current);
        list.sort(new MajSort());
        return validFan(list, player.getShow(), player.getDiscard(), gameInfo);
    }


    private HuRecord validFan(List<Maj> majs, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo) {
        return huValidHandler.validFan(majs, show, discard, gameInfo);
    }
}
