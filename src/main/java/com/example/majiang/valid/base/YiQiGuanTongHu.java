package com.example.majiang.valid.base;

import com.example.majiang.*;
import com.example.majiang.valid.Hu;
import com.example.majiang.valid.HuValid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Hu
public class YiQiGuanTongHu implements HuValid {
    private static final MajGroup[] SHUNZI_WAN = new MajGroup[]{
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(0, 0), new GroupMaj(0, 1), new GroupMaj(0, 2))),
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(0, 3), new GroupMaj(0, 4), new GroupMaj(0, 5))),
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(0, 6), new GroupMaj(0, 7), new GroupMaj(0, 8)))};
    private static final MajGroup[] SHUNZI_TONG = new MajGroup[]{
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(1, 0), new GroupMaj(1, 1), new GroupMaj(1, 2))),
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(1, 3), new GroupMaj(1, 4), new GroupMaj(1, 5))),
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(1, 6), new GroupMaj(1, 7), new GroupMaj(1, 8)))};
    private static final MajGroup[] SHUNZI_TIAO = new MajGroup[]{
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(2, 0), new GroupMaj(2, 1), new GroupMaj(2, 2))),
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(2, 3), new GroupMaj(2, 4), new GroupMaj(2, 5))),
            new MajGroup(MajGroup.SHUN_ZI, Arrays.asList(new GroupMaj(2, 6), new GroupMaj(2, 7), new GroupMaj(2, 8)))};
    private static final MajGroup[][] YI_QI_GUAN_TONG = new MajGroup[][]{SHUNZI_WAN, SHUNZI_TONG, SHUNZI_TIAO};


    private boolean contain(List<MajGroup> tmp, MajGroup target) {
        for (int i = 0; i < tmp.size(); i++) {
            MajGroup c = tmp.get(i);
            if (c.equals(target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> handMajGroups, GameInfo gameInfo) {
        List<MajGroup> tmp = new ArrayList<>();
        tmp.addAll(show);
        tmp.addAll(handMajGroups);
        int shunzinum = 0;
        for (MajGroup majGroup : tmp) {
            if (majGroup.getType() == MajGroup.SHUN_ZI) {
                shunzinum++;
            }
        }
        if (shunzinum < 3) {
            return null;
        }

        for (MajGroup[] majGroups : YI_QI_GUAN_TONG) {
            int yiqi = 0;
            for (MajGroup majGroup : majGroups) {
                if (contain(tmp, majGroup)) {
                    yiqi++;
                }
            }
            if (yiqi == 3) {
                return Fan.YI_QI_GUAN_TONG;
            }
        }
        return null;
    }
}
