package com.example.majiang.valid;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

public class QiXiaoDuiHu extends SpecialHuValid {

    @Override
    public Fan valid0(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
        int duiNum = 0;
        for (int n : wan) {
            if (n == 2) duiNum++;
        }
        for (int n : suo) {
            if (n == 2) duiNum++;
        }
        for (int n : tong) {
            if (n == 2) duiNum++;
        }
        for (int n : zi) {
            if (n == 2) duiNum++;
        }
        return duiNum == 7 ? Fan.QI_XIAO_DUI : null;
    }
}
