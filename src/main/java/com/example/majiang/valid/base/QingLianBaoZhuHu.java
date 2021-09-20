package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

public class QingLianBaoZhuHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard,List<MajGroup> list) {
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
