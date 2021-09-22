package com.example.majiang.valid;

import com.example.majiang.*;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 15:10
 */
public interface PreHuValid extends BaseValid {
    public PreValidRes valid0(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo);
}
