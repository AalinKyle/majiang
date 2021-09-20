package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class QingLianBaoZhuHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        return check(wan) || check(tong) || check(suo) ? Fan.QING_LIAN_BAO_ZHU : null;
    }

    private boolean check(int[] arr) {
        if (v(arr, 14)) {
            if (arr[0] < 3) {
                return false;
            }
            if (arr[8] < 3) {
                return false;
            }
            for (int i = 1; i < 8; i++) {
                if (arr[i] == 0) return false;
            }
            return true;
        } else return false;
    }
}
