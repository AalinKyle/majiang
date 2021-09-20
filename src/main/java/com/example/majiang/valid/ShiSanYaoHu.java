package com.example.majiang.valid;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

public class ShiSanYaoHu extends SpecialHuValid {
    @Override
    public Fan valid0(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
        int sum = 0;
        if (wan[0] > 0) {
            sum += 1;
        }
        if (wan[8] > 0) {
            sum += 1;
        }
        if (suo[0] > 0) {
            sum += 1;
        }
        if (suo[8] > 0) {
            sum += 1;
        }
        if (tong[0] > 0) {
            sum += 1;
        }
        if (tong[8] > 0) {
            sum += 1;
        }
        for (int i = 0; i < zi.length; i++) {
            if (zi[i] > 0) {
                sum += 1;
            }
        }
        return sum >= 13 ? Fan.SHI_SNA_YAO : null;
    }
}
