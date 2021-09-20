package com.example.majiang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fan {
    public final static Fan PI_HU = new Fan(1, "屁胡");
    public final static Fan QI_XIAO_DUI = new Fan(2, "七小对");
    public final static Fan QING_YI_SE = new Fan(6, "清一色");
    public final static Fan HUN_YI_SE = new Fan(3, "混一色");
    public final static Fan DUI_DUI_HU = new Fan(3, "对对胡");
    public final static Fan SHI_SNA_YAO = new Fan(12, "十三幺");
    public final static Fan ZI_YI_SE = new Fan(12, "字一色");
    public final static Fan DA_SI_XI = new Fan(24, "大四喜");
    public final static Fan XIAO_SI_XI = new Fan(12, "小四喜");
    public final static Fan DA_SAN_YUAN = new Fan(12, "大三元");
    public final static Fan XIAO_SAN_YUAN = new Fan(3, "小三元");
    public final static Fan QING_LIAN_BAO_ZHU = new Fan(12, "青莲宝珠");
    private int num;
    private String type;
}
