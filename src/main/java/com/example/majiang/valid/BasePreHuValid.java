package com.example.majiang.valid;

import com.example.majiang.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BasePreHuValid implements PreHuValid {

    /**
     * 实现//a*AAA+b*ABC+DD 基本胡型 七小对，十三幺不算
     *
     * @param show
     * @param discard
     * @return
     */
    @Override
    public PreValidRes valid0(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo) {
        List<MajGroup> list = new LinkedList<>();
        int[] wan = hmd.getWan();
        int[] suo = hmd.getSuo();
        int[] tong = hmd.getTong();
        int[] zi = hmd.getZi();
        return new PreValidRes(baseHu(wan, suo, tong, zi, show, discard, list, gameInfo), list);
    }

    private int[] copy(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    private boolean baseHu(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard, List<MajGroup> list, GameInfo gameInfo) {
        ShowEswnzfbx eswnzfbx = parseShow(show);
        int sunzi = eswnzfbx.getSuoShunZi();
        int ke = eswnzfbx.getMingKeNum();
        int gang = eswnzfbx.getGangNum();
        int gs = sunzi + ke + gang;
        return checkGpn(wan, tong, suo, zi, 4 - gs, 1, list);

    }

    private boolean checkGpn(int[] wan, int[] tong, int[] suo, int[] zi, int targetGs, int targetQt, List<MajGroup> list) {
        int[][] t = new int[][]{wan, tong, suo, zi};
        boolean[] haveShunZi = new boolean[]{true, true, true, false};
        int gs = 0, qt = 0;
        for (int i = 0; i < t.length; i++) {
            Gpn gw = parse(t[i], i, haveShunZi[i], list);
            if (gw == null) {
                return false;
            }
            qt += gw.qt;
            gs += gw.gs;
        }
        return targetGs == gs && targetQt == qt;
    }

    private Gpn parse(int[] majs, int type, boolean haveSunZi, List<MajGroup> list) {
        Gpn gpn = new Gpn();
        if (bfs(majs, 0, gpn, haveSunZi, list, type)) {
            return gpn;
        } else {
            return null;
        }
    }

    private boolean bfs(int[] majs, int index, Gpn gpn, boolean haveSunZi, List<MajGroup> mgs, int type) {
        if (allSame(majs, 0)) {
            return true;
        }
        int i = index;
        while (i < majs.length) {
            if (majs[i] > 0) {
                if (majs[i] > 2) {
                    majs[i] -= 3;
                    gpn.gs++;
                    mgs.add(new MajGroup(MajGroup.MING_KE, Arrays.asList(new GroupMaj(type, i), new GroupMaj(type, i), new GroupMaj(type, i))));
                    if (bfs(majs, i, gpn, haveSunZi, mgs, type)) {
                        return true;
                    } else {
                        mgs.remove(mgs.size() - 1);
                        majs[i] += 3;
                        gpn.gs--;
                    }
                }
                if (majs[i] > 1 && gpn.qt == 0) {
                    majs[i] -= 2;
                    gpn.qt++;
                    mgs.add(new MajGroup(MajGroup.QT, Arrays.asList(new GroupMaj(type, i), new GroupMaj(type, i))));
                    if (bfs(majs, i, gpn, haveSunZi, mgs, type)) {
                        return true;
                    } else {
                        mgs.remove(mgs.size() - 1);
                        majs[i] += 2;
                        gpn.qt--;
                    }
                }
                if (haveSunZi && i < 7 && majs[i + 1] > 0 && majs[i + 2] > 0) {
                    majs[i]--;
                    majs[i + 1]--;
                    majs[i + 2]--;
                    mgs.add(new MajGroup(MajGroup.QT, Arrays.asList(new GroupMaj(type, i), new GroupMaj(type, i + 1), new GroupMaj(type, i + 2))));
                    gpn.gs++;
                    if (bfs(majs, i, gpn, haveSunZi, mgs, type)) {
                        return true;
                    } else {
                        mgs.remove(mgs.size() - 1);
                        majs[i]++;
                        majs[i + 1]++;
                        majs[i + 2]++;
                        gpn.gs--;
                        return false;
                    }
                } else {
                    return false;
                }
            }
            if (majs[i] == 0) {
                i++;
            }
        }
        return false;
    }


    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    static class Gpn {
        private int gs;
        private int qt;
    }

}
