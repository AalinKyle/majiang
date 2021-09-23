package com.example.majiang.p;

import com.example.majiang.*;

import java.util.Arrays;
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
        int[][] tmp = new int[][]{
                {sw, Maj.WAN}, {ss, Maj.TONG}, {st, Maj.SUO}, {sz, Maj.ZI}
        };
        Arrays.sort(tmp, Comparator.comparingInt(o -> o[0]));


        int zeroNum = 0;
        for (int i = 0; i < tmp.length - 1; i++) {
            if (tmp[i][0] == 0) {
                zeroNum++;
            }
        }
        boolean haveToRemoveZi = zeroNum == 2 && sz < hand.size() / 2;
        for (int[] t : tmp) {
            if (t[0] > 0) {
                if (t[1] != Maj.ZI && !haveToRemoveZi) {
                    for (int i = 0; i < hand.size(); i++) {
                        if (hand.get(i).getType() == t[1]) {
                            return hand.remove(i);
                        }
                    }
                }

            }
        }
        return super.play(gameInfo);
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) sum += i;
        return sum;
    }
}
