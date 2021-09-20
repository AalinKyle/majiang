package com.example.majiang;

import com.example.majiang.p.Player;
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
    private static Map<Fan, List<HuMaj>> record = new HashMap<>();
    public static AtomicInteger gameNum = new AtomicInteger(0);

    private Hus hus;

    public MajGame(Hus hus) {
        this.hus = hus;
        valids = hus.getHus();
    }

    public void clear() {
        table = null;
    }

    public void play(List<Player<Maj>> players) {
        try {
            gameNum.incrementAndGet();
            table = new MajTable();
            table.shuffle();
            table.addPlayers(players);
            table.deal();
            int no = 0;
            HuRecord huRecord;
            Maj current;
            int shou = 0;
            while (table.canTouch()) {
                shou++;
                Player<Maj> player = players.get(no % players.size());
                player.touch(table.touch());
                huRecord = validFan(player);
                if (huRecord != null) {
                    log.info("第{}局{}手,{} 自摸胡=>{},番数=>{}", gameNum.get(), shou, player.getName(), huRecord.getHuMaj(), huRecord.getFans());
                    checkZiMoPoint(player, no, players, huRecord.getFans());
                    rec(huRecord);
                    return;
                }
                current = player.play();
                for (int i = 1; i < 4; i++) {
                    /**
                     * 按顺序
                     */
                    Player<Maj> p = players.get((no + i) % players.size());
                    huRecord = validFan(p, current);
                    if (huRecord != null) {
                        log.info("第{}局{}手,{} 胡 {} 放炮=>{},番数=>{}", gameNum.get(), shou, p.getName(), player.getName(), huRecord.getHuMaj(), huRecord.getFans());
                        checkFangPaoPoint(p, player, huRecord.getFans());
                        rec(huRecord);
                        return;
                    }
                }
                no++;
            }
        } finally {
            for (Player<Maj> player : players) {
                player.over();
            }
        }
    }

    private void checkZiMoPoint(Player<Maj> get, int no, List<Player<Maj>> all, List<Fan> fans) {
        int fanNum = sumFan(fans);
        get.addPoint(fanNum * 1000);
        for (int i = 1; i < 4; i++) {
            Player<Maj> p = all.get((no + i) % all.size());
            p.addPoint(-fanNum * 1000 / 3);
        }
    }

    private void checkFangPaoPoint(Player<Maj> get, Player<Maj> lost, List<Fan> fans) {
        int fanNum = sumFan(fans);
        get.addPoint(fanNum * 1000);
        lost.addPoint(-fanNum * 1000);
    }

    private int sumFan(List<Fan> list) {
        int sum = 0;
        for (Fan f : list) {
            sum += f.getNum();
        }
        return sum;
    }


    private void rec(HuRecord huRecord) {
        for (Fan fan : huRecord.getFans()) {
            List<HuMaj> list = record.getOrDefault(fan, new ArrayList<>());
            list.add(huRecord.getHuMaj());
            record.put(fan, list);
        }
    }

    /**
     * 自摸
     *
     * @param player
     * @return
     */
    private HuRecord validFan(Player<Maj> player) {
        return validFan(player.getHand(), player.getShow(), player.getDiscard());
    }

    /**
     * 自摸
     *
     * @param player
     * @return
     */
    private HuRecord validFan(Player<Maj> player, Maj current) {
        List<Maj> list = new ArrayList<>();
        list.addAll(player.getHand());
        list.add(current);
        list.sort(new MajSort());
        return validFan(list, player.getShow(), player.getDiscard());
    }

    private HuRecord validFan(List<Maj> majs, List<MajGroup> show, List<Maj> discard) {
        List<Fan> fans = new ArrayList<>();
        for (HuValid v : valids) {
            HandMajDistribution distribution = new HandMajDistribution(majs);
            Fan fan = v.valid0(distribution.getWan(), distribution.getTong(), distribution.getSuo(), distribution.getZi(), show, discard);
            if (fan != null) {
                fans.add(fan);
            }
        }
        if (fans.size() > 0) {
            return new HuRecord(new Date(), fans, new HuMaj(majs, show));
        } else return null;
    }
}
