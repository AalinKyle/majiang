package com.example.majiang.point;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.p.Player;

import java.util.List;

public interface CalPointHandler {

    void checkZiMoPoint(Player<Maj> get, List<Player<Maj>> all, List<Fan> fans);

    void checkFangPaoPoint(Player<Maj> get, Player<Maj> lost, List<Fan> fans);
}
