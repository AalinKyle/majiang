package com.example.demo2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

public class ssds {

    @Test
    public void as() {
        System.out.println(parse(new int[]{2, 2, 2, 3, 2, 1, 0, 1, 1}));
    }

    private boolean checkGpn(int[] wan, int[] tong, int[] suo, int[] zi, int targetGs, int targetQt) {
        int[][] t = new int[][]{wan, tong, suo, zi};
        int gs = 0, qt = 0;
        for (int[] ta : t) {
            Gpn gw = parse(ta);
            if (gw == null) return false;
            qt += gw.qt;
            gs += gw.gs;
        }
        return targetGs == gs && targetQt == qt;
    }

    private Gpn parse(int[] majs) {
        Gpn gpn = new Gpn();
        if (bfs(majs, 0, gpn)) return gpn;
        else return null;
    }

    //1 1 1 2 3 4 4 4 5 5 5 6 6 6
    private boolean bfs(int[] majs, int index, Gpn gpn) {
        if (allSame(majs, 0)) return true;
        int i = index;
        while (i < majs.length) {
            if (majs[i] > 0) {
                if (majs[i] > 2) {
                    majs[i] -= 3;
                    gpn.gs++;
                    if (bfs(majs, i, gpn)) return true;
                    else {
                        majs[i] += 3;
                        gpn.gs--;
                    }
                }
                if (majs[i] > 1 && gpn.qt == 0) {
                    majs[i] -= 2;
                    gpn.qt++;
                    if (bfs(majs, i, gpn)) return true;
                    else {
                        majs[i] += 2;
                        gpn.qt--;
                    }
                }
                if (i < 7 && majs[i + 1] > 0 && majs[i + 2] > 0) {
                    majs[i]--;
                    majs[i + 1]--;
                    majs[i + 2]--;
                    gpn.gs++;
                    if (bfs(majs, i, gpn)) return true;
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
}
