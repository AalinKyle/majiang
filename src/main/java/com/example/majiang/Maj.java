package com.example.majiang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maj {
    private int type;
    private int content;
    public static final int WAN = 0;
    public static final int TONG = 1;
    public static final int SUO = 2;
    public static final int ZI = 3;
    public static final int ZI_DONG = 0;
    public static final int ZI_NAN = 1;
    public static final int ZI_XI = 2;
    public static final int ZI_BEI = 3;
    public static final int ZI_BAI = 4;
    public static final int ZI_FA = 5;
    public static final int ZI_ZHONG = 6;
    public static final String[] zi = new String[]{"东", "南", "西", "北", "白", "发", "中"};
    public static final String[] types = new String[]{"万", "筒", "索", "字"};
    public static final String[] contents = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九"};

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (type != ZI) {
            sb.append(contents[content]).append(types[type]);
        } else {
            sb.append(zi[content]);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || (!(o instanceof Maj))) {
            return false;
        }
        Maj maj = (Maj) o;
        return type == maj.type &&
                content == maj.content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content);
    }
}
