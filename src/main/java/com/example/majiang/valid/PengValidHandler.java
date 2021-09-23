package com.example.majiang.valid;

import com.example.majiang.GameInfo;
import com.example.majiang.Maj;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:36
 */
public interface PengValidHandler {
    public PengRecord validPeng(List<Maj> hand, GameInfo gameInfo);

}
