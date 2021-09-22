package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.ArrayList;
import java.util.List;

@Hu
public class SanSeTongKeHu implements HuValid {
    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {

        List<MajGroup> tmp = new ArrayList<>();
        tmp.addAll(show);
        tmp.addAll(list);
        List<MajGroup> ke = new ArrayList<>();
        for (MajGroup majGroup : tmp) {//字牌没有同刻
            if ((majGroup.getType() == MajGroup.MING_KE || majGroup.getType() == MajGroup.AN_KE) && majGroup.getMajs().get(0).getType() != 3) {
                ke.add(majGroup);
            }
        }
        if (ke.size() < 3) {
            return null;
        }

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
        if (m1.getType() != MajGroup.SHUN_ZI || m2.getType() != MajGroup.SHUN_ZI) {
            return false;
        }
        List<GroupMaj> majs1 = m1.getMajs();
        List<GroupMaj> majs2 = m2.getMajs();
        if (majs1.get(0).getType() == majs2.get(0).getType()) {
            return false;
        }
        return majs1.get(0).getContent() == majs2.get(0).getContent();
    }

}
