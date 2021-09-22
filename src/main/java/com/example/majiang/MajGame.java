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

@Data
@Slf4j
public class MajGame {
    private MajTable table;
    private static Map<String, List<HuMaj>> record = new HashMap<>();
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
    private GangValidHandler mingGangValidHandler;
    private GangValidHandler anGangValidHandler;
    private PengValidHandler pengValidHandler;
    private ChiValidHandler chiValidHandler;

    public MajGame(Hus hus) {
        this.hus = hus;
        huValidHandler = new DefaultHuValidHandler(hus);
        pointHandler = new DefaultCalPointHandler();
        tableInfoHandler = new DefaultTableInfoHandler();
        baoPaiHandler = new DefaultBaoPaiHandler();
        mingGangValidHandler = new MingGangValidHandler();
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

    private void changeDealer(Integer winnerNo, List<Player<Maj, MajGroup>> players) {
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
        table.addPlayers(players);
        table.deal();
        baoPaiHandler.init(table);
        /**
         * 放入开局摸的宝牌
         */
        baoPaiHandler.putBaoPai(baoPaiHandler.touchOpeningBaoPai());
    }


    public void play(List<Player<Maj, MajGroup>> players) {
        try {
            prePlay(players);
            int no = dealer;
            HuRecord huRecord;
            Maj currentMaj;
            int shou = 0;
            Integer winnerNo = null;
            boolean over = false;
            while (table.canTouch() && !over) {
                boolean discard = true;
                shou++;
                // 当前获取player
                Player<Maj, MajGroup> player = players.get(no % players.size());
                //摸牌
                currentMaj = table.touch();
                player.touch(currentMaj);
                //判断有没有自摸
                /**
                 *摸牌时的game info
                 */
                GameInfo touchGameInfo = buildGameInfo(players, player, currentMaj, true);
                huRecord = validFan(player, touchGameInfo);
                if (huRecord != null && huRecord.isHu()) {
                    log.info("第{}局,场风{}庄家{},宝牌{},{}手,{} 自摸胡=>{},番数:{}", gameNum.get(), Maj.zi[changFen], players.get(dealer).getName(), baoPaiHandler.getBaoPais(), shou, player.getName(), huRecord.getHuMaj(), buildFanString(huRecord.getFans()));
                    //算自摸分
                    pointHandler.checkZiMoPoint(player, players, huRecord);
                    winnerNo = tmpInfos.get(player.getName()).getPlayerNo();
                    //记录
                    rec(huRecord);
                    discard = false;
                    /**
                     * 自摸不存在一炮多响
                     */
                    break;
                }
                GangRecord anGangRecord = validAnGang(player, touchGameInfo);
                if (anGangRecord != null && anGangRecord.isGang()) {
                    player.addShow(anGangRecord.getGroup(), anGangRecord.getNeedRemoveHand());
                    /**
                     * 杠完摸一张牌，并且开一张宝牌
                     */
                    player.touch(table.touch());
                    baoPaiHandler.putBaoPai(baoPaiHandler.touchGangBaoPai());
                }
                //弃牌
                currentMaj = player.play(buildGameInfo(players, player, null, false));
                for (int i = 1; i < players.size(); i++) {
                    Player<Maj, MajGroup> other = players.get((no + i) % players.size());
                    //判断没有没有放炮
                    huRecord = validFan(other, currentMaj, buildGameInfo(players, other, currentMaj, false));
                    if (huRecord != null && huRecord.isHu()) {
                        log.info("第{}局,场风{}庄家{},宝牌{},{}手,{} 胡 {} 放炮=>{},番数:{}", gameNum.get(), Maj.zi[changFen], players.get(dealer).getName(), baoPaiHandler.getBaoPais(), shou, other.getName(), player.getName(), huRecord.getHuMaj(), buildFanString(huRecord.getFans()));
                        //算放炮分
                        pointHandler.checkFangPaoPoint(other, player, huRecord);
                        //记录
                        rec(huRecord);
                        winnerNo = tmpInfos.get(other.getName()).getPlayerNo();
                        /**
                         * 可能一炮多响，要把其他玩家都判断了。
                         */
                        discard = false;
                        over = true;
                    }
                }
                /**
                 * 明杠
                 */

                boolean fulu = true;
                for (int i = 1; i < players.size(); i++) {
                    Player<Maj, MajGroup> other = players.get((no + i) % players.size());
                    //判断没有没有放炮
                    GangRecord mingGangRecord = validMingGang(other, buildGameInfo(players, other, currentMaj, false));
                    if (mingGangRecord != null && mingGangRecord.isGang()) {
                        fulu = false;
                        other.addShow(mingGangRecord.getGroup(), mingGangRecord.getNeedRemoveHand());
                        /**
                         * 杠完摸一张牌，并且开一张宝牌
                         */
                        other.touch(table.touch());
                        baoPaiHandler.putBaoPai(baoPaiHandler.touchGangBaoPai());

                        //TODO 需要实现吃碰杠下 逻辑顺序的转换，之前逻辑行不通
                        currentMaj = other.play(buildGameInfo(players, other, null, true));
                        discard = false;
                        /**
                         * 不存在多个人都杠，所以一个人杠完就可以break
                         */
                        break;
                    }
                }
                /**
                 * 碰
                 */
                if (fulu) {
                    for (int i = 1; i < players.size(); i++) {
                        fulu = false;
                        /**
                         * 按顺序
                         */
                        Player<Maj, MajGroup> other = players.get((no + i) % players.size());
                        //判断没有没有放炮
                        huRecord = validPeng(other, currentMaj, buildGameInfo(players, other, currentMaj, false));
                        if (huRecord != null && huRecord.isHu()) {
                            /**
                             * 可能一炮多响，要把其他玩家都判断了。
                             */
                            discard = false;
                            over = true;
                        }
                    }
                }
                /**
                 * 吃
                 */
                if (fulu) {
                    for (int i = 1; i < players.size(); i++) {
                        /**
                         * 按顺序
                         */
                        Player<Maj, MajGroup> other = players.get((no + i) % players.size());
                        //判断没有没有放炮
                        huRecord = validChi(other, currentMaj, buildGameInfo(players, other, currentMaj, false));
                        if (huRecord != null && huRecord.isHu()) {
                            /**
                             * 可能一炮多响，要把其他玩家都判断了。
                             */
                            discard = false;
                            over = true;
                        }
                    }
                }

                /**
                 * 如果没有被胡吃碰杠，就进入弃牌堆
                 */
                if (discard) {
                    player.addDiscard(currentMaj);
                }
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


    private GangRecord validAnGang(Player<Maj, MajGroup> player, GameInfo touchGameInfo) {
        List<Maj> hand = player.getHand();
        return anGangValidHandler.validGang(hand, touchGameInfo);
    }

    private GangRecord validMingGang(Player<Maj, MajGroup> player, GameInfo touchGameInfo) {
        List<Maj> hand = player.getHand();
        return mingGangValidHandler.validGang(hand, touchGameInfo);
    }


    //-----------------------------------------------------------------------------------------------------------------------------------//

    private GameInfo buildGameInfo(List<Player<Maj, MajGroup>> all, Player<Maj, MajGroup> player, Maj current, boolean zimo) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setBaoPai(baoPaiHandler.getBaoPais());
        gameInfo.setChangFeng(changFen);
        gameInfo.setZiFeng(calZiFeng(player));
        gameInfo.setPlayerTableInfos(buildPlayerTableInfoMap(all, player));
        gameInfo.setZimo(zimo);
        gameInfo.setCurrent(current);
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
        for (Fan fan : list) {
            sum += fan.getNum();
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

    private void record(Player<Maj, MajGroup> winner, List<Player<Maj, MajGroup>> loser, GameInfo gameInfo, HuRecord record) {

    }

    private void rec(HuRecord huRecord) {
        for (Fan fan : huRecord.getFans()) {
            List<HuMaj> list = record.getOrDefault(fan.getType(), new ArrayList<>());
            list.add(huRecord.getHuMaj());
            record.put(fan.getType(), list);
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
