package com.example.majiang.valid.base;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.ArrayList;
import java.util.List;

public class SanSeTongShunHu extends BaseHuValid {
    @Override
    public Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard, List<MajGroup> list) {
        List<MajGroup> tmp = new ArrayList<>();
        tmp.addAll(show);
        tmp.addAll(list);
        List<MajGroup> shunzi = new ArrayList<>();
        for (MajGroup majGroup : tmp) {
            if (majGroup.getType() == MajGroup.SHUN_ZI) shunzi.add(majGroup);
        }
        if (shunzi.size() < 3) return null;

        for (int i = 0; i < shunzi.size() - 2; i++) {
            MajGroup ii = shunzi.get(i);
            for (int j = i + 1; j < shunzi.size() - 1; j++) {
                MajGroup jj = shunzi.get(j);
                if (tongsun(ii, jj)) {
                    for (int k = j + 1; k < shunzi.size(); k++) {
                        MajGroup kk = shunzi.get(k);//略微有点愚蠢，以后再改
                        if (tongsun(ii, kk) && tongsun(jj, kk)) return Fan.SAN_SE_TONG_SHUN;
                    }
                }
            }
        }
        return null;
    }

    private boolean tongsun(MajGroup m1, MajGroup m2) {
        if (m1.getType() != MajGroup.SHUN_ZI || m2.getType() != MajGroup.SHUN_ZI) return false;
        List<Maj> majs1 = m1.getMajs();
        List<Maj> majs2 = m2.getMajs();
        if (majs1.get(0).getType() == majs2.get(0).getType()) return false;
        for (int i = 0; i < majs1.size(); i++) {
            if (majs1.get(i).getContent() != majs2.get(i).getContent()) {
                return false;
            }
        }
        return true;
    }

}
