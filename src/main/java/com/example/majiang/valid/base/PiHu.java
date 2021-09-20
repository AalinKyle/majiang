package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

public class PiHu extends BaseHuValid {

    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard,List<MajGroup> list) {
        return Fan.PI_HU;
    }


}
