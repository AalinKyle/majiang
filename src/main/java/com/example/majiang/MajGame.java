package com.example.majiang;

import com.example.majiang.valid.HuValid;
import com.example.majiang.valid.base.*;
import com.example.majiang.valid.spc.QiXiaoDuiHu;
import com.example.majiang.valid.spc.ShiSanYaoHu;
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
    private static AtomicInteger gameNum = new AtomicInteger(0);

    public MajGame(List<HuValid> valids) {
        this.valids = valids;
    }

    public void clear() {
        table = null;
    }

    public void play(List<Player<Maj>> players) {
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
                log.info("第{}局{}手,自摸胡=>{},番数=>{}", gameNum.get(), shou, huRecord.getHuMaj(), huRecord.getFans());
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
                    log.info("第{}局{}手,放炮胡=>{},番数=>{}", gameNum.get(), shou, huRecord.getHuMaj(), huRecord.getFans());
                    rec(huRecord);
                    return;
                }
            }
            no++;
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
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                MajGame game = new MajGame(getHus());
                while (true) {
                    List<Player<Maj>> players = findPlayer();
                    game.play(players);
                    game.clear();
                }
            }).start();
        }
    }

    private static List<Player<Maj>> findPlayer() {
        List<Player<Maj>> players = new ArrayList<>();
        Player<Maj> a = new Player<Maj>("东", new MajSort());
        Player<Maj> b = new Player<Maj>("南", new MajSort());
        Player<Maj> c = new Player<Maj>("西", new MajSort());
        Player<Maj> d = new Player<Maj>("北", new MajSort());
        players.add(a);
        players.add(b);
        players.add(c);
        players.add(d);
        return players;
    }

    private static List<HuValid> getHus() {
        List<HuValid> huValids = new ArrayList<>();
        huValids.add(new DuanYaoJiuHu());
        huValids.add(new HunQuanDaiYaoJiuHu());
        huValids.add(new YiQiGuanTongHu());
        huValids.add(new SanSeTongShunHu());
        huValids.add(new SanSeTongKeHu());
        huValids.add(new HunLaoTouHu());
        huValids.add(new QingLaoTouHu());
        huValids.add(new YiBeiKouHu());
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
        huValids.add(new SiAnKeHu());
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
