package com.example.majiang.valid;

import com.example.majiang.GameInfo;
import com.example.majiang.HandMajDistribution;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

/**
 * @author
 */
public class NoValidPreHuValid implements PreHuValid {
    @Override
    public PreValidRes valid0(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo) {
        return new PreValidRes(true, null);
    }
}
