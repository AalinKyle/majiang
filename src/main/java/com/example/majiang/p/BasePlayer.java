package com.example.majiang.p;

import com.example.majiang.GameInfo;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.User;
import com.example.majiang.valid.ChiRecord;
import com.example.majiang.valid.FuluRecord;
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
public class BasePlayer implements Player<Maj, MajGroup> {

    private User user;

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
        show.add(group);
        hand.removeAll(discard);
    }

    private String name;
    private boolean enableLog;
    private List<Maj> hand;
    private List<MajGroup> show;
    private List<Maj> discard;

    private Comparator<Maj> sort;

    @Override
    public List<Maj> getHand() {
        return hand;
    }

    public BasePlayer(User user, Comparator<Maj> sort, boolean enableLog) {
        this.name = user.getName();
        hand = new LinkedList<>();
        show = new LinkedList<>();
        discard = new LinkedList<>();
        this.sort = sort;
        this.enableLog = enableLog;
        this.user = user;
    }

    public BasePlayer(User user, Comparator<Maj> sort) {
        this(user, sort, false);
    }

    @Override
    public void touch(Maj maj) {
        touch(maj, sort);
    }

    @Override
    public void touch(Maj maj, Comparator<Maj> sort) {
        info("{}=>摸了{}", name, maj);
        hand.add(maj);
        hand.sort(sort);
    }

    private Random random = new Random();

    @Override
    public Maj play(GameInfo gameInfo) {
        Maj play = hand.remove(random.nextInt(hand.size()));
        info("{}=>打出了{}", name, play);
        return play;
    }

    @Override
    public void addDiscard(Maj t) {
        discard.add(t);
    }

    protected void info(String str, Object... arg) {
        if (enableLog) {
            log.info(str, arg);
        }
    }

    @Override
    public void over() {
        hand = new LinkedList<>();
        show = new LinkedList<>();
        discard = new LinkedList<>();
    }

    @Override
    public FuluRecord chooseGang(GangRecord record, GameInfo gameInfo) {
        if (record != null && record.isGang()) {
            return record.getRecords().get(0);
        } else {
            return null;
        }

    }

    @Override
    public FuluRecord choosePeng(PengRecord record, GameInfo gameInfo) {
        if (record != null && record.isPeng()) {
            return record.getRecords().get(0);
        } else {
            return null;
        }
    }

    @Override
    public FuluRecord chooseChi(ChiRecord record, GameInfo gameInfo) {
        if (record != null && record.isChi()) {
            return record.getRecords().get(0);
        } else {
            return null;
        }
    }
}
