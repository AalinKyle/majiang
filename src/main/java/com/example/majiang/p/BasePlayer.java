package com.example.majiang.p;

import com.example.majiang.*;
import com.example.majiang.valid.ChiRecord;
import com.example.majiang.valid.FuluObj;
import com.example.majiang.valid.GangRecord;
import com.example.majiang.valid.PengRecord;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Data
@Slf4j
@NoArgsConstructor
/**
 * @author kyle
 * */
public class BasePlayer implements Player<Maj, MajGroup> {
    protected User user;

    @Override
    public void addPoint(int n) {
        user.getPoint().addAndGet(n);
    }

    /**
     * @param group
     * @param discard 吃碰杠完需要把手牌转移到副露那里
     */
    @Override
    public void addShow(MajGroup group, List<Maj> discard) {
        addShow(group, discard, null);
    }

    @Override
    public void addShow(MajGroup group, List<Maj> discard, List<MajGroup> needRemoveShow) {

        if (discard != null && discard.size() > 0) {
            for (Maj maj : discard) {
                hand.remove(maj);
            }
        }
        if (needRemoveShow != null && needRemoveShow.size() > 0) {
            for (MajGroup gm : needRemoveShow) {
                show.remove(gm);
            }
        }
        show.add(group);
    }

    protected String name;
    protected boolean enableLog;
    protected List<Maj> hand;
    protected List<MajGroup> show;
    protected List<Maj> discard;
    protected Random random = new Random();
    protected Comparator<Maj> sort;

    @Override
    public List<Maj> getHand() {
        return hand;
    }

    public BasePlayer(User user, Comparator<Maj> sort, boolean enableLog) {
        this.name = user.getName();
        this.sort = sort;
        this.enableLog = enableLog;
        this.user = user;
        initMajs();
    }

    /**
     * 初始化牌区
     */
    private void initMajs() {
        hand = new LinkedList<>();
        show = new LinkedList<>();
        discard = new LinkedList<>();
    }

    public BasePlayer(User user, Comparator<Maj> sort) {
        this(user, sort, false);
    }


    @Override
    public void over() {
        initMajs();
        info("--------------------------------------------------------------------");
    }


    @Override
    public void addDiscard(Maj maj) {
        info("{}=>弃了{}=>{}", name, maj, hand);
        discard.add(maj);
    }

    protected void info(String str, Object... arg) {
        if (enableLog) {
            log.info(str, arg);
        }
    }

    @Override
    public void touch(Maj maj) {
        touch(maj, sort);
    }

    @Override
    public void touch(Maj maj, Comparator<Maj> sort) {
        info("{}=>摸了{}=>{}", name, maj, hand);
        hand.add(maj);
        hand.sort(sort);
    }

    @Override
    public Maj play(GameInfo gameInfo) {
        if (hand.size() < 2) {
            info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx{}", name);
        }
        int index = random.nextInt(hand.size());
        info("{}=>打出了{}=>{},{}", name, hand.get(index), hand, hand.size());
        Maj play = hand.remove(index);

        return play;
    }

    @Override
    public FuluObj chooseGang(List<GangRecord> records, GameInfo gameInfo) {
        if (records != null && records.size() > 0) {
            info("{}杠了{}=>{}", name, records.get(0).getRecords().get(0), hand);
            return records.get(0).getRecords().get(0);
        } else {
            return null;
        }

    }

    @Override
    public FuluObj choosePeng(PengRecord record, GameInfo gameInfo) {
        if (record != null && record.isPeng()) {
            info("{}碰了{}=>{}", name, record.getRecords().get(0), hand);
            return record.getRecords().get(0);
        } else {
            return null;
        }
    }

    @Override
    public FuluObj chooseChi(ChiRecord record, GameInfo gameInfo) {
        if (record != null && record.isChi()) {
            info("{}吃了{}=>{}", name, record.getRecords().get(0), hand);
            return record.getRecords().get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean chooseHu(HuRecord huRecord, GameInfo touchGameInfo) {
        return true;
    }
}
