package com.example.majiang.valid;

import com.example.majiang.*;

import java.util.List;

public interface HuValid {
    Fan valid0(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo);

    default boolean v(int[] arr, int num) {
        return sum(arr) == num;
    }

    default int sum(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return sum;
    }

    default boolean allSame(int[] arr, int target) {
        for (int i : arr) {
            if (i != target) return false;
        }
        return true;
    }

    default ShowEswnzfbx parseShow(List<MajGroup> groups) {
        ShowEswnzfbx.ShowEswnzfbxBuilder builder = ShowEswnzfbx.builder();
        builder.groupNum(groups.size());
        int wanShunZi = 0;
        int tongShunZi = 0;
        int suoShunZi = 0;

        int wanMingKe = 0;
        int tongMingKe = 0;
        int suoMingKe = 0;
        int fengMingKe = 0;
        int yiMingKe = 0;

        int wanAnGang = 0;
        int tongAnGang = 0;
        int suoAnGang = 0;
        int fengAnGang = 0;
        int yiAnGang = 0;

        int wanMingGang = 0;
        int tongMingGang = 0;
        int suoMingGang = 0;
        int fengMingGang = 0;
        int yiMingGang = 0;
        if (groups.size() > 0) {
            for (MajGroup group : groups) {
                Maj maj = group.getMajs().get(0);
                switch (group.getType()) {
                    case MajGroup.SHUN_ZI: {
                        if (maj.getType() == 0) {
                            wanShunZi++;
                        }
                        if (maj.getType() == 1) {
                            tongShunZi++;
                        }
                        if (maj.getType() == 2) {
                            suoShunZi++;
                        }
                        break;
                    }
                    case MajGroup.MING_KE: {
                        if (maj.getType() == 0) {
                            wanMingKe++;
                        }
                        if (maj.getType() == 1) {
                            tongMingKe++;
                        }
                        if (maj.getType() == 2) {
                            suoMingKe++;
                        }
                        if (maj.getType() == 3) {
                            if (maj.getContent() <= 3)
                                fengMingGang++;
                            else {
                                yiMingKe++;
                            }
                        }
                        break;
                    }
                    case MajGroup.AN_GANG: {
                        if (maj.getType() == 0) {
                            wanAnGang++;
                        }
                        if (maj.getType() == 1) {
                            tongAnGang++;
                        }
                        if (maj.getType() == 2) {
                            suoAnGang++;
                        }
                        if (maj.getType() == 3) {
                            if (maj.getContent() <= 3)
                                fengAnGang++;
                            else {
                                yiAnGang++;
                            }
                        }
                        break;
                    }
                    case MajGroup.MING_GANG: {
                        if (maj.getType() == 0) {
                            wanMingGang++;
                        }
                        if (maj.getType() == 1) {
                            tongMingGang++;
                        }
                        if (maj.getType() == 2) {
                            suoMingGang++;
                        }
                        if (maj.getType() == 3) {
                            if (maj.getContent() <= 3)
                                fengMingGang++;
                            else {
                                yiMingGang++;
                            }
                        }
                        break;
                    }
                }
                builder.wanShunZi(wanShunZi).wanMingKe(wanMingKe).wanAnGang(wanAnGang).wanMingGang(wanMingGang);
                builder.tongShunZi(tongShunZi).tongMingKe(tongMingKe).tongAnGang(tongAnGang).tongMingGang(tongMingGang);
                builder.suoShunZi(suoShunZi).suoMingKe(suoMingKe).suoAnGang(suoAnGang).suoMingGang(suoMingGang);
                builder.fengMingKe(fengMingKe).fengAnGang(fengAnGang).fengMingGang(fengMingGang);
                builder.yiMingKe(yiMingKe).yiAnGang(yiAnGang).yiMingGang(yiMingGang);
            }
        }
        return builder.build();
    }
}
