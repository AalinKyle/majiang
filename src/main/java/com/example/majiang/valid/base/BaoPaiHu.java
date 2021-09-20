package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class BaoPaiHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        List<Maj> baoPai = gameInfo.getBaoPai();

        if (baoPai != null && baoPai.size() > 0) {
            int[] wan = hmd.getWan();
            int[] suo = hmd.getSuo();
            int[] tong = hmd.getTong();
            int[] zi = hmd.getZi();
            int fan = 0;
            for (Maj bao : baoPai) {
                int type = bao.getType();
                int content = bao.getContent();
                switch (type) {
                    case 0: {
                        fan += wan[content];
                        break;
                    }
                    case 1: {
                        fan += tong[content];
                        break;
                    }
                    case 2: {
                        fan += suo[content];
                        break;
                    }
                    case 3: {
                        fan += zi[content];
                        break;
                    }
                }
            }
            if (fan != 0) {
                return new Fan(fan, "宝牌");
            }
        }
        return null;
    }
}
