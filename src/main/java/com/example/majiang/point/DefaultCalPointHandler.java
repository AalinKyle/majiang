package com.example.majiang.point;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.p.Player;

import java.util.List;

public class DefaultCalPointHandler implements CalPointHandler {
    @Override
    public void checkZiMoPoint(Player<Maj> get, List<Player<Maj>> all, List<Fan> fans) {
        int fanNum = sumFan(fans, 1000);
        get.addPoint(fanNum);
        for (int i = 0; i < all.size(); i++) {
            Player<Maj> p = all.get(i);
            if (p != get)
                p.addPoint(-fanNum / 3);
        }
    }

    @Override
    public void checkFangPaoPoint(Player<Maj> get, Player<Maj> lost, List<Fan> fans) {
        int fanNum = sumFan(fans, 1000);
        get.addPoint(fanNum);
        lost.addPoint(-fanNum);
    }

    private int sumFan(List<Fan> list, int beishu) {
        int sum = 0;
        for (Fan f : list) {
            sum += f.getNum();
        }
        return sum * beishu;
    }
}
