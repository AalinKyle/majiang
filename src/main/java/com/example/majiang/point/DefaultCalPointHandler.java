package com.example.majiang.point;

import com.example.majiang.Fan;
import com.example.majiang.HuRecord;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.p.Player;

import java.util.List;

public class DefaultCalPointHandler implements CalPointHandler {
    @Override
    public void checkZiMoPoint(Player<Maj, MajGroup> get, List<Player<Maj, MajGroup>> all, HuRecord record) {
        List<Fan> fans = record.getFans();
        int fanNum = sumFan(fans, 1000);
        get.addPoint(fanNum);
        for (int i = 0; i < all.size(); i++) {
            Player<Maj, MajGroup> p = all.get(i);
            if (p != get) {
                p.addPoint(-fanNum / 3);
            }
        }
    }

    @Override
    public void checkFangPaoPoint(Player<Maj, MajGroup> get, Player<Maj, MajGroup> lost, HuRecord record) {
        List<Fan> fans = record.getFans();
        int fanNum = sumFan(fans, 1000);
        get.addPoint(fanNum);
        lost.addPoint(-fanNum);
    }

    private int sumFan(List<Fan> list, int beishu) {
        int sum = 0;
        boolean yiMan = false;
        for (Fan f : list) {
            if (f.isYiMan()) {
                yiMan = true;
            }
            sum += f.getNum();
        }
        return sum * beishu * (yiMan ? 36 : 1);
    }
}
