package com.example.majiang.valid;

import com.example.majiang.Fan;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

public abstract class BaseHuValid implements HuValid {

    /**
     * 实现//a*AAA+b*ABC+DD 基本胡型 七小对，十三幺不算
     *
     * @param wan
     * @param tong
     * @param suo
     * @param zi
     * @param show
     * @param discard
     * @return
     */
    @Override
    public Fan valid0(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
        if (baseHu(copy(wan), copy(tong), copy(suo), copy(zi), show, discard)) {
            return valid(copy(wan), copy(tong), copy(suo), copy(zi), show, discard);
        }
        return null;
    }

    private int[] copy(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    private boolean baseHu(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard) {
        ShowEswnzfbx eswnzfbx = parseShow(show);
        int sunzi = eswnzfbx.getSuoShunZi();
        int ke = eswnzfbx.getMingKeNum();
        int gang = eswnzfbx.getGangNum();
        int gs = sunzi + ke + gang;
        return checkGpn(wan, tong, suo, zi, 4 - gs, 1);

    }

    private boolean checkGpn(int[] wan, int[] tong, int[] suo, int[] zi, int targetGs, int targetQt) {
        int[][] t = new int[][]{wan, tong, suo, zi};
        boolean[] haveShunZi = new boolean[]{true, true, true, false};
        int gs = 0, qt = 0;
        for (int i = 0; i < t.length; i++) {
            Gpn gw = parse(t[i], haveShunZi[i]);
            if (gw == null) return false;
            qt += gw.qt;
            gs += gw.gs;
        }
        return targetGs == gs && targetQt == qt;
    }

    private Gpn parse(int[] majs, boolean haveSunZi) {
        Gpn gpn = new Gpn();
        if (bfs(majs, 0, gpn, haveSunZi)) return gpn;
        else return null;
    }

    private boolean bfs(int[] majs, int index, Gpn gpn, boolean haveSunZi) {
        if (allSame(majs, 0)) return true;
        int i = index;
        while (i < majs.length) {
            if (majs[i] > 0) {
                if (majs[i] > 2) {
                    majs[i] -= 3;
                    gpn.gs++;
                    if (bfs(majs, i, gpn, haveSunZi)) return true;
                    else {
                        majs[i] += 3;
                        gpn.gs--;
                    }
                }
                if (majs[i] > 1 && gpn.qt == 0) {
                    majs[i] -= 2;
                    gpn.qt++;
                    if (bfs(majs, i, gpn, haveSunZi)) return true;
                    else {
                        majs[i] += 2;
                        gpn.qt--;
                    }
                }
                if (haveSunZi && i < 7 && majs[i + 1] > 0 && majs[i + 2] > 0) {
                    majs[i]--;
                    majs[i + 1]--;
                    majs[i + 2]--;
                    gpn.gs++;
                    if (bfs(majs, i, gpn, haveSunZi)) return true;
                    else {
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
            if (majs[i] == 0) i++;
        }
        return false;
    }

    private boolean allSame(int[] arr, int target) {
        for (int i : arr) {
            if (i != target) return false;
        }
        return true;
    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    static class Gpn {
        private int gs;
        private int qt;
    }

    public abstract Fan valid(int[] wan, int[] tong, int[] suo, int[] zi, List<MajGroup> show, List<Maj> discard);
}
