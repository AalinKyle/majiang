package com.example.majiang;

import lombok.Data;

import java.util.List;

@Data
public class MajGroup {
    /**
     * 雀头  AA
     */
    public static final int QT = 0;
    /**
     * ABC
     */
    public static final int SHUNZI = 1;
    public static final int MING_KE = 2;
    public static final int AN_GANG = 3;
    public static final int MING_GUANG = 4;


    private int type;
    private List<Maj> majs;
}
