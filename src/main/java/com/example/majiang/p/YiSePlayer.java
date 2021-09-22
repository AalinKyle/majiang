package com.example.majiang.p;

import com.example.majiang.*;

import java.util.Comparator;
import java.util.List;

/**
 * 清一色和混一色
 */
public class YiSePlayer extends BasePlayer {
    public YiSePlayer() {
    }

    public YiSePlayer(User user, Comparator<Maj> sort, boolean enableLog) {
        super(user, sort, enableLog);
    }

    public YiSePlayer(User user, Comparator<Maj> sort) {
        super(user, sort);
    }

    @Override
    public Maj play(GameInfo gameInfo) {
        List<Maj> hand = getHand();
        HandMajDistribution distribution = new HandMajDistribution(hand);
        int[] wan = distribution.getWan();
        int[] suo = distribution.getSuo();
        int[] tong = distribution.getTong();
        int[] zi = distribution.getZi();
        int sw = sum(wan);
        int ss = sum(suo);
        int st = sum(tong);
        int sz = sum(zi);
        if (sw < ss & sw < st) {

        } else if (ss < sw && ss < sz) {

        } else if (sz < ss && sz < sw) {
        } else {

        }//todo  除了这个还有      吃碰需要实现

        return super.play(gameInfo);
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) sum += i;
        return sum;
    }
}
