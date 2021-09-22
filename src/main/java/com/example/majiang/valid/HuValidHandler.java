package com.example.majiang.valid;

import com.example.majiang.GameInfo;
import com.example.majiang.HuRecord;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 14:46
 */
public interface HuValidHandler {
    public HuRecord validFan(List<Maj> majs, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo);
}
