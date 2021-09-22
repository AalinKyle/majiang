package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.List;
@Hu
public class BaoPaiHu implements HuValid {
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
                    case Maj.WAN: {
                        fan += wan[content];
                        break;
                    }
                    case Maj.TONG: {
                        fan += tong[content];
                        break;
                    }
                    case Maj.SUO: {
                        fan += suo[content];
                        break;
                    }
                    case Maj.ZI: {
                        fan += zi[content];
                        break;
                    }
                }
            }
            if (fan != 0) {
                return new Fan(fan, "宝牌", false, false, true);
            }
        }
        return null;
    }
}
