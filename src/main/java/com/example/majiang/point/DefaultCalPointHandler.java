package com.example.majiang.point;

import com.example.majiang.*;
import com.example.majiang.utils.PointUtils;

import java.util.List;

public class DefaultCalPointHandler implements CalPointHandler {

    @Override
    public void checkZiMoPoint(PointInterface get, List<PointInterface> all, HuRecord record) {
        List<Fan> fans = record.getFans();
        HuMaj huMaj = record.getHuMaj();
        GameInfo gameInfo = huMaj.getGameInfo();
        PointInterface dealer = record.getDealer();
        int point = PointUtils.calPoint(huMaj, fans, gameInfo.isDealer());
        if (gameInfo.isDealer()) {
            for (int i = 0; i < all.size(); i++) {
                PointInterface p = all.get(i);
                if (p != get) {
                    p.addPoint(-point / 3);
                }
            }
        } else {
            for (PointInterface player : all) {
                if (player != get) {
                    player.addPoint(-point / (player == dealer ? 2 : 4));
                }
            }
        }
        get.addPoint(point);
    }

    @Override
    public void checkFangPaoPoint(PointInterface get, PointInterface lost, HuRecord record) {
        List<Fan> fans = record.getFans();
        HuMaj huMaj = record.getHuMaj();
        GameInfo gameInfo = huMaj.getGameInfo();
        int point = PointUtils.calPoint(huMaj, fans, gameInfo.isDealer());
        get.addPoint(point);
        lost.addPoint(-point);
    }

}
