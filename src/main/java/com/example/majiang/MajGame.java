package com.example.majiang;

import com.example.majiang.feng.TableInfoHandler;
import com.example.majiang.feng.DefaultTableInfoHandler;
import com.example.majiang.feng.MajTableInfo;
import com.example.majiang.p.Player;
import com.example.majiang.point.CalPointHandler;
import com.example.majiang.point.DefaultCalPointHandler;
import com.example.majiang.valid.HuValid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Slf4j
public class MajGame {
    private MajTable table;
    private List<HuValid> valids;
    private static Map<String, List<HuMaj>> record = new HashMap<>();
    public static AtomicInteger gameNum = new AtomicInteger(0);
    private Map<String, GameTmp> tmpInfos;
    private List<Maj> baoPai;
    /**
     * playerNo 为dealer 的人就是庄家 也就是东
     */
    private int dealer;
    private int changFen = 0;
    private Hus hus;
    private CalPointHandler pointHandler = new DefaultCalPointHandler();
    private TableInfoHandler tableInfoHandler = new DefaultTableInfoHandler();

    public MajGame(Hus hus) {
        this.hus = hus;
        valids = hus.getHus();
    }

    public void clear() {
        table = null;
    }

    private void initTmpInfos(List<Player<Maj>> players) {
        tmpInfos = new HashMap<>(players.size());
        for (int i = 0; i < players.size(); i++) {
            tmpInfos.put(players.get(i).getName(), new GameTmp(i));
        }
    }

    private void changeDealer(Integer winnerNo, List<Player<Maj>> players) {
        MajTableInfo tableInfo = tableInfoHandler.changeDealer(winnerNo, dealer, changFen, players.size());
        this.dealer = tableInfo.getDealer();
        this.changFen = tableInfo.getChangFeng();
    }

    public void play(List<Player<Maj>> players) {
        try {
            gameNum.incrementAndGet();
            table = new MajTable();
            table.shuffle();
            table.addPlayers(players);
            baoPai = new ArrayList<>();
            initTmpInfos(players);
            table.deal();
            int no = dealer;
            HuRecord huRecord;
            Maj current;
            int shou = 0;
            Integer winnerNo = null;

            baoPai.add(touchBaoPai());
            boolean over = false;
            while (table.canTouch() && !over) {
                shou++;
                Player<Maj> player = players.get(no % players.size());
                player.touch(table.touch());
                huRecord = validFan(player, new GameInfo(changFen, calZiFeng(player), baoPai));
                if (huRecord != null && huRecord.isHu()) {
                    log.info("第{}局,场风{}庄家{},宝牌{},{}手,{} 自摸胡=>{},番数:{}", gameNum.get(), Maj.zi[changFen], players.get(dealer).getName(), baoPai, shou, player.getName(), huRecord.getHuMaj(), buildFanString(huRecord.getFans()));
                    pointHandler.checkZiMoPoint(player, players, huRecord.getFans());
                    winnerNo = tmpInfos.get(player.getName()).getPlayerNo();
                    rec(huRecord);
                    /**
                     * 自摸不存在多炮
                     */
                    break;
                }
                current = player.play();
                for (int i = 1; i < 4; i++) {
                    /**
                     * 按顺序
                     */
                    Player<Maj> p = players.get((no + i) % players.size());
                    huRecord = validFan(p, current, new GameInfo(changFen, calZiFeng(p), baoPai));
                    if (huRecord != null && huRecord.isHu()) {
                        log.info("第{}局,场风{}庄家{},宝牌{},{}手,{} 胡 {} 放炮=>{},番数:{}", gameNum.get(), Maj.zi[changFen], players.get(dealer).getName(), baoPai, shou, p.getName(), player.getName(), huRecord.getHuMaj(), buildFanString(huRecord.getFans()));
                        pointHandler.checkFangPaoPoint(p, player, huRecord.getFans());
                        rec(huRecord);
                        winnerNo = tmpInfos.get(p.getName()).getPlayerNo();
                        /**
                         * 可能一炮多响
                         */
                        over = true;
                    }
                }
                no++;
            }
            changeDealer(winnerNo, players);
        } finally {
            for (Player<Maj> player : players) {
                player.over();
            }
        }
    }

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
                    prefixNum *= fan.getNum();
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
        Map<String, Integer> map = new HashMap<>();
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

    private Maj touchBaoPai() {
        Maj touch = table.touch();
        int type = touch.getType();
        int content = touch.getContent();
        if (type == Maj.ZI) {
            return new Maj(type, (content + 1) % 7);
        } else {
            return new Maj(type, (content + 1) % 9);
        }
    }

    private int calZiFeng(Player<Maj> player) {
        GameTmp gameTmp = tmpInfos.get(player.getName());
        if (gameTmp == null) throw new NullPointerException();
        return tableInfoHandler.calZiFeng(gameTmp.getPlayerNo(), dealer);
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
    private HuRecord validFan(Player<Maj> player, GameInfo gameInfo) {
        return validFan(player.getHand(), player.getShow(), player.getDiscard(), gameInfo);
    }

    /**
     * 自摸
     *
     * @param player
     * @return
     */
    private HuRecord validFan(Player<Maj> player, Maj current, GameInfo gameInfo) {
        List<Maj> list = new ArrayList<>();
        list.addAll(player.getHand());
        list.add(current);
        list.sort(new MajSort());
        return validFan(list, player.getShow(), player.getDiscard(), gameInfo);
    }

    private HuRecord validFan(List<Maj> majs, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo) {
        List<Fan> fans = new ArrayList<>();
        boolean hu = false;
        boolean isYiMan = false;
        for (HuValid v : valids) {
            HandMajDistribution distribution = new HandMajDistribution(majs);
            Fan fan = v.valid0(distribution, show, discard, gameInfo);
            if (fan != null) {
                if (fan.isCanHu()) hu = true;
                /**
                 * 当胡的牌出现役满牌型，就不要计算其他的小胡了，只统计役满
                 */
                if (fan.isYiMan()) {
                    if (!isYiMan) fans.clear();
                    isYiMan = true;
                }
                if (isYiMan) {
                    if (fan.isYiMan()) {
                        fans.add(fan);
                    }
                } else {
                    fans.add(fan);
                }
            }
        }
        if (fans.size() > 0) {
            return new HuRecord(isYiMan, hu, new Date(), fans, new HuMaj(majs, show));
        } else return null;
    }
}
