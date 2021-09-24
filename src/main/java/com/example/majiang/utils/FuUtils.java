package com.example.majiang.utils;

import com.example.majiang.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/24 14:42
 */
public class FuUtils {
    private static final int INITIAL_VALUE = 20;

    public static int calFu(HuMaj huMaj, List<Fan> fans) {
        List<MajGroup> show = huMaj.getShow();
        GameInfo gameInfo = huMaj.getGameInfo();
        boolean zimo = gameInfo.isZimo();
        List<MajGroup> all = new ArrayList<>();
        List<MajGroup> handMajGroup = huMaj.getHandMajGroup();
        if (handMajGroup != null) {
            all.addAll(show);
        }
        if (handMajGroup != null) {
            all.addAll(handMajGroup);
        }
        int changFeng = gameInfo.getChangFeng();
        int ziFeng = gameInfo.getZiFeng();
        int fu = INITIAL_VALUE;
        for (Fan f : fans) {
            if ("平胡".equals(f.getType())) {
                fu += 2;
                break;
            } else if (Fan.QI_XIAO_DUI == f) {
                return 25;
            }
        }
        for (MajGroup h : all) {
            switch (h.getType()) {
                case MajGroup.QT: {
                    GroupMaj maj = h.getMajs().get(0);
                    int type = maj.getType();
                    int content = maj.getContent();
                    if (maj.isYi() || (type == Maj.ZI && (content == ziFeng || content == changFeng))) {
                        fu += 2;
                    }
                    break;
                }
                case MajGroup.MING_KE: {
                    fu += h.containYaoJiu() ? 4 : 2;
                    break;
                }
                case MajGroup.AN_KE: {
                    fu += h.containYaoJiu() ? 8 : 4;
                    break;
                }
                case MajGroup.MING_GANG: {
                    fu += h.containYaoJiu() ? 16 : 8;
                    break;
                }
                case MajGroup.AN_GANG: {
                    fu += h.containYaoJiu() ? 32 : 16;
                    break;
                }
                default: {
                }
            }
        }

        if (zimo) {
            fu += 2;
        }

        return fu;
    }
}
