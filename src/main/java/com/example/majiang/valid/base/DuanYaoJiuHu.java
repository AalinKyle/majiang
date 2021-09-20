package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.List;

public class DuanYaoJiuHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard,List<MajGroup> list) {
        if (wan[0] == 0 && wan[8] == 0 && tong[0] == 0 && tong[8] == 0 && suo[0] == 0 && suo[8] == 0 && allSame(zi, 0) && duanyaojiu(show)) {
            return Fan.DUAN_YAO_JIU;
        } else {
            return null;
        }
    }

    private boolean duanyaojiu(List<MajGroup> show) {
        for (MajGroup majGroup : show) {
            List<Maj> majs = majGroup.getMajs();
            for (Maj maj : majs) {
                if (maj.getType() == 3 || maj.getContent() == 0 || maj.getContent() == 8) {
                    return false;
                }
            }
        }
        return true;
    }
}
