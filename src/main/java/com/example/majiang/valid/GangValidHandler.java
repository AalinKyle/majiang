package com.example.majiang.valid;

import com.example.majiang.GameInfo;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.p.Player;

/**
 * @Author kyle
 * @create 2021/9/22 16:37
 */
public interface GangValidHandler {

    GangRecord validGang(Player<Maj, MajGroup> playe, GameInfo gameInfo);

}
