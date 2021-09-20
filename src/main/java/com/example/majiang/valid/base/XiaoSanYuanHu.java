package com.example.majiang.valid.base;

import com.example.majiang.*;

import java.util.List;

public class XiaoSanYuanHu extends BaseHuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        int[] zi = hmd.getZi();
        ShowEswnzfbx eswnzfbx = parseShow(show);
        int yiMingKe = eswnzfbx.getYiMingKe();
        int yiMingGang = eswnzfbx.getYiMingGang();
        int yiAnGang = eswnzfbx.getYiAnGang();
        boolean dsyQt = false;
        int dsyDui = 0;
        for (int i = 4; i < zi.length; i++) {
            if (zi[i] == 3) dsyDui++;
            else if (zi[i] == 2) {
                if (dsyQt) return null;
                else {
                    dsyQt = true;
                }

            }
        }

        return dsyDui + yiMingKe + yiMingGang + yiAnGang == 2 && dsyQt ? Fan.XIAO_SAN_YUAN : null;
    }
}
