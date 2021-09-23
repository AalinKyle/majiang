package com.example.majiang.valid;

import com.example.majiang.GameInfo;
import com.example.majiang.Maj;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:37
 */
public interface ChiValidHandler {
    ChiRecord validChi(List<Maj> hand, GameInfo gameInfo);
}
