package com.example.majiang;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fan {
    public final static Fan YI_HU = new Fan(1, "役牌");
    public final static Fan CHANG_FENG_HU = new Fan(1, "场风牌");
    public final static Fan ZI_FENG_HU = new Fan(1, "自风牌");
    public final static Fan PI_HU = new Fan(1, "屁胡");
    public final static Fan DUAN_YAO_JIU = new Fan(1, "断幺九");
    public final static Fan HUN_QUAN_DAI_YAO_JIU = new Fan(2, "混全带幺九");
    public final static Fan SAN_SE_TONG_SHUN = new Fan(2, "三色同顺");
    public final static Fan SAN_SE_TONG_KE = new Fan(2, "三色同刻");
    public final static Fan HUN_LAO_TOU = new Fan(2, "混老头");
    public final static Fan QING_LAO_TOU = new Fan(1, "清老头", true);
    public final static Fan YI_QI_GUAN_TONG = new Fan(2, "一气贯通");
    public final static Fan YI_BEI_KOU = new Fan(2, "一杯口", false, false, false);
    public final static Fan QI_XIAO_DUI = new Fan(2, "七小对");
    public final static Fan QING_YI_SE = new Fan(6, "清一色");
    public final static Fan HUN_YI_SE = new Fan(3, "混一色");
    public final static Fan DUI_DUI_HU = new Fan(3, "对对胡");
    public final static Fan SI_AN_KE = new Fan(1, "四暗刻", true);
    public final static Fan SHI_SNA_YAO = new Fan(1, "十三幺", true);
    public final static Fan ZI_YI_SE = new Fan(1, "字一色", true);
    public final static Fan DA_SI_XI = new Fan(2, "大四喜", true);
    public final static Fan XIAO_SI_XI = new Fan(1, "小四喜", true);
    public final static Fan DA_SAN_YUAN = new Fan(1, "大三元", true);
    public final static Fan XIAO_SAN_YUAN = new Fan(2, "小三元");
    public final static Fan SI_GANG_ZI = new Fan(1, "四杠子", true);
    public final static Fan QING_LIAN_BAO_ZHU = new Fan(1, "青莲宝珠", true);
    private int num;
    private String type;
    private boolean yiMan;
    private boolean canHu = true;
    private boolean isSingle;

    public Fan(int num, String type) {
        this(num, type, false, true, false);
    }

    public Fan(int num, String type, boolean yiMan) {
        this(num, type, yiMan, true, false);
    }

    public Fan(int num, String type, boolean yiMan, boolean canHu, boolean isSingle) {
        this.num = num;
        this.type = type;
        this.yiMan = yiMan;
        this.canHu = canHu;
        this.isSingle = isSingle;
    }

}
