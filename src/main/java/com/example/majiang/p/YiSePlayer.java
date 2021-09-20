package com.example.majiang.p;

import com.example.majiang.HandMajDistribution;
import com.example.majiang.Maj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 清一色和混一色
 */
public class YiSePlayer extends Player<Maj> {
    public YiSePlayer() {
    }

    public YiSePlayer(String name, Comparator<Maj> sort, boolean enableLog) {
        super(name, sort, enableLog);
    }

    public YiSePlayer(String name, Comparator<Maj> sort) {
        super(name, sort);
    }

    @Override
    public Maj play() {
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

        }

        return super.play();
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) sum += i;
        return sum;
    }
}
