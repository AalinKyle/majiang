package com.example.majiang.valid;

import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 15:12
 */
public interface BaseValid {
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
            if (i != target) {
                return false;
            }
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
                        if (maj.getType() == Maj.WAN) {
                            wanShunZi++;
                        }
                        if (maj.getType() == Maj.TONG) {
                            tongShunZi++;
                        }
                        if (maj.getType() == Maj.SUO) {
                            suoShunZi++;
                        }
                        break;
                    }
                    case MajGroup.MING_KE: {
                        if (maj.getType() == Maj.WAN) {
                            wanMingKe++;
                        }
                        if (maj.getType() == Maj.TONG) {
                            tongMingKe++;
                        }
                        if (maj.getType() == Maj.SUO) {
                            suoMingKe++;
                        }
                        if (maj.getType() == Maj.ZI) {
                            if (maj.getContent() <= 3)
                                fengMingGang++;
                            else {
                                yiMingKe++;
                            }
                        }
                        break;
                    }
                    case MajGroup.AN_GANG: {
                        if (maj.getType() == Maj.WAN) {
                            wanAnGang++;
                        }
                        if (maj.getType() == Maj.TONG) {
                            tongAnGang++;
                        }
                        if (maj.getType() == Maj.SUO) {
                            suoAnGang++;
                        }
                        if (maj.getType() == Maj.ZI) {
                            if (maj.getContent() <= 3)
                                fengAnGang++;
                            else {
                                yiAnGang++;
                            }
                        }
                        break;
                    }
                    case MajGroup.MING_GANG: {
                        if (maj.getType() == Maj.WAN) {
                            wanMingGang++;
                        }
                        if (maj.getType() == Maj.TONG) {
                            tongMingGang++;
                        }
                        if (maj.getType() == Maj.SUO) {
                            suoMingGang++;
                        }
                        if (maj.getType() == Maj.ZI) {
                            if (maj.getContent() <= 3)
                                fengMingGang++;
                            else {
                                yiMingGang++;
                            }
                        }
                        break;
                    }
                    default: {
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
