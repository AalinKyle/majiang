package com.example.majiang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maj {
    private int type;
    private int content;

    public static final String[] zi = new String[]{"东", "南", "西", "北", "白", "发", "中"};
    public static final String[] types = new String[]{"万", "筒", "索", "字"};
    public static final String[] contents = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九"};

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (type != 3) {
            sb.append(contents[content]).append(types[type]);
        } else {
            sb.append(zi[content]);
        }
        return sb.toString();
    }
}
