package com.example.majiang.point;

import com.example.majiang.HuRecord;
import com.example.majiang.PointInterface;

import java.util.List;

public interface CalPointHandler {

    void checkZiMoPoint(PointInterface get, List<PointInterface> all, HuRecord record);

    void checkFangPaoPoint(PointInterface get, PointInterface lost,  HuRecord record);
}
