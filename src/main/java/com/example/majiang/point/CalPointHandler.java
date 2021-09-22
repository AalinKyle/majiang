package com.example.majiang.point;

import com.example.majiang.HuRecord;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.p.Player;

import java.util.List;

public interface CalPointHandler {

    void checkZiMoPoint(Player<Maj, MajGroup> get, List<Player<Maj, MajGroup>> all, HuRecord record);

    void checkFangPaoPoint(Player<Maj,MajGroup> get, Player<Maj,MajGroup> lost,  HuRecord record);
}
