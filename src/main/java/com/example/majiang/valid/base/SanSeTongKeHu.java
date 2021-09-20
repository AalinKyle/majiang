package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.ArrayList;
import java.util.List;

public class SanSeTongKeHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard, List<MajGroup> list) {
        List<MajGroup> tmp = new ArrayList<>();
        tmp.addAll(show);
        tmp.addAll(list);
        List<MajGroup> ke = new ArrayList<>();
        for (MajGroup majGroup : tmp) {//字牌没有同刻
            if ((majGroup.getType() == MajGroup.MING_KE || majGroup.getType() == MajGroup.AN_KE) && majGroup.getMajs().get(0).getType() != 3)
                ke.add(majGroup);
        }
        if (ke.size() < 3) return null;

        for (int i = 0; i < ke.size() - 2; i++) {
            MajGroup ii = ke.get(i);
            for (int j = i + 1; j < ke.size() - 1; j++) {
                MajGroup jj = ke.get(j);
                if (tongke(ii, jj)) {
                    for (int k = j + 1; k < ke.size(); k++) {
                        MajGroup kk = ke.get(k);//略微有点愚蠢，以后再改
                        if (tongke(ii, kk) && tongke(jj, kk)) return Fan.SAN_SE_TONG_KE;
                    }
                }
            }
        }
        return null;
    }

    private boolean tongke(MajGroup m1, MajGroup m2) {
        if (m1.getType() != MajGroup.SHUN_ZI || m2.getType() != MajGroup.SHUN_ZI) return false;
        List<Maj> majs1 = m1.getMajs();
        List<Maj> majs2 = m2.getMajs();
        if (majs1.get(0).getType() == majs2.get(0).getType()) return false;
        return majs1.get(0).getContent() == majs2.get(0).getContent();
    }

}
