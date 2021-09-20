package com.example.majiang;

import com.example.majiang.valid.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class MajGame {
    private MajTable table;
    private List<HuValid> valids;
    private Map<Fan, List<HuMaj>> record = new HashMap<>();
    private int gameNum = 0;

    public MajGame(List<HuValid> valids) {
        this.valids = valids;
    }

    public void clear() {
        table = null;
    }

    public void play(List<Player<Maj>> players) {
        gameNum++;
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
            Player<Maj> player = players.get(no++ % players.size());
            player.touch(table.touch());
            huRecord = validFan(player);
            if (huRecord != null) {
                log.info("第{}局{}手，自摸胡=>{},番数=>{}", gameNum, shou, huRecord.getHuMaj(), huRecord.getFans());
                rec(huRecord);
                return;
            }
            current = player.play();
            for (Player<Maj> p : players) {
                if (p != player) {
                    huRecord = validFan(p, current);
                    if (huRecord != null) {
                        log.info("第{}局{}手,放炮胡=>{},番数=>{}", gameNum, shou, huRecord.getHuMaj(), huRecord.getFans());
                        rec(huRecord);
                        return;
                    }
                }
            }

        }

    }


    private void rec(HuRecord huRecord) {
        for (Fan fan : huRecord.getFans()) {
            List<HuMaj> list = record.getOrDefault(fan, new ArrayList<>());
            list.add(huRecord.getHuMaj());
            record.put(fan, list);
        }
    }

    public static void main(String[] args) {
        MajGame game = new MajGame(getHus());
        while (true) {
            List<Player<Maj>> players = findPlayer();
            game.play(players);
            game.clear();
        }
    }

    private static List<Player<Maj>> findPlayer() {
        List<Player<Maj>> players = new ArrayList<>();
        Player<Maj> a = new Player<Maj>(new MajSort());
        Player<Maj> b = new Player<Maj>(new MajSort());
        Player<Maj> c = new Player<Maj>(new MajSort());
        Player<Maj> d = new Player<Maj>(new MajSort());
        players.add(a);
        players.add(b);
        players.add(c);
        players.add(d);
        return players;
    }

    private static List<HuValid> getHus() {
        List<HuValid> huValids = new ArrayList<>();
        huValids.add(new PiHu());
        huValids.add(new ShiSanYaoHu());
        huValids.add(new QiXiaoDuiHu());
        huValids.add(new QingYiSeHu());
        huValids.add(new HunYiSeHu());
        huValids.add(new DuiDuiHu());
        huValids.add(new ZiYiSeHu());
        huValids.add(new DaSiXiHu());
        huValids.add(new XiaoSiXiHu());
        huValids.add(new DaSanYuanHu());
        huValids.add(new XiaoSanYuanHu());
        huValids.add(new QingLianBaoZhuHu());
        return huValids;
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
