package com.example.majiang.valid;

import com.example.majiang.GameInfo;
import com.example.majiang.GroupMaj;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:40
 */
public class DefaultChiValidHandler implements ChiValidHandler {
    @Override
    public ChiRecord validChi(List<Maj> hand, GameInfo gameInfo) {
        Maj currentMaj = gameInfo.getCurrentMaj();
        if (currentMaj == null) {
            throw new NullPointerException();
        }
        int type = currentMaj.getType();
        if (type == Maj.ZI) {
            return new ChiRecord(false);
        } else {
            List<FuluObj> list = new ArrayList<>();
            for (int[] t : T) {
                FuluObj check = check(currentMaj, hand, t);
                if (check != null) {
                    list.add(check);
                }
            }
            return list.size() == 0 ? new ChiRecord(false) : new ChiRecord(true, list);
        }
    }

    static final int[][] T = new int[][]{
            {0, 1, 2}, {-1, 0, 1}, {-2, -1, 0}
    };

    private FuluObj check(Maj maj, List<Maj> hand, int[] t) {
        int type = maj.getType();
        int content = maj.getContent();
        List<Maj> needRemove = new ArrayList<>();
        List<GroupMaj> groupMajs = new ArrayList<>();
        for (int tt : t) {
            int index = content + tt;
            if (index < 0 || index > 8) {
                return null;
            }
            if (tt != 0) {
                needRemove.add(new Maj(type, index));
            }
            groupMajs.add(new GroupMaj(type, index, tt == 0));
        }
        if (contains(hand, needRemove)) {
            MajGroup majGroup = new MajGroup(MajGroup.SHUN_ZI, groupMajs);
            return new FuluObj(majGroup, needRemove);
        } else {
            return null;
        }
    }

    private boolean contains(List<Maj> hand, List<Maj> needRemove) {
        for (Maj maj : needRemove) {
            if (!hand.contains(maj)) {
                return false;
            }
        }
        return true;
    }
}
