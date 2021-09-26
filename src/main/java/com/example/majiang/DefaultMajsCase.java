package com.example.majiang;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/26 10:55
 */
public class DefaultMajsCase implements MajsCase {

    private static final int SINGLE_NUM = 4;

    @Override
    public List<Maj> getMajs() {
        List<Maj> pool = new ArrayList<>();
        for (int i = 0; i < SINGLE_NUM; i++) {
            for (int j = 0; j < Maj.contents.length; j++) {
                pool.add(new Maj(Maj.WAN, j));
                pool.add(new Maj(Maj.TONG, j));
                pool.add(new Maj(Maj.SUO, j));
            }
            for (int j = 0; j < Maj.zi.length; j++) {
                pool.add(new Maj(Maj.ZI, j));
            }
        }
        return pool;
    }
}
