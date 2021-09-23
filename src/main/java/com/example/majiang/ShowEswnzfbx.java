package com.example.majiang;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShowEswnzfbx {

    private int groupNum;

    private int wanShunZi;
    private int tongShunZi;
    private int suoShunZi;

    private int wanMingKe;
    private int tongMingKe;
    private int suoMingKe;
    /**
     * 东南西北 碰
     */
    private int fengMingKe;
    /**
     * 中发白 碰
     */
    private int yiMingKe;

    private int wanAnGang;
    private int tongAnGang;
    private int suoAnGang;
    private int fengAnGang;
    private int yiAnGang;


    private int wanMingGang;
    private int tongMingGang;
    private int suoMingGang;
    private int fengMingGang;
    private int yiMingGang;

    public boolean onlyHave(int type) {
        if (groupNum == 0) return false;
        switch (type) {
            case 0: {
                //万
                return groupNum == wanShunZi + wanMingKe + wanMingGang + wanAnGang;
            }
            case 1: {
                //筒
                return groupNum == tongShunZi + tongMingKe + tongMingGang + tongAnGang;
            }
            case 2: {
                //索
                return groupNum == suoShunZi + suoMingKe + suoMingGang + suoAnGang;
            }
            case 3: {
                //字
                return groupNum == fengMingKe + fengMingGang + fengAnGang + yiMingKe + yiMingGang + yiAnGang;
            }
            default:
                return false;
        }
    }

    public int getSunziNum() {
        return wanShunZi + suoShunZi + tongShunZi;
    }

    public int getMingKeNum() {
        return wanMingKe + suoMingKe + tongMingKe + fengMingKe + yiMingKe;
    }

    public int getMingGangNum() {
        return wanMingGang + suoMingGang + tongMingGang + fengMingGang + yiMingGang;
    }

    public int getAnGangNum() {
        return wanAnGang + suoAnGang + tongAnGang + fengAnGang + yiAnGang;
    }

    public int getGangNum() {
        return getMingGangNum() + getAnGangNum();
    }

    public int getWan() {
        return wanShunZi + wanMingGang + wanAnGang + wanMingKe;
    }

    public int getSuo() {
        return suoShunZi + suoMingGang + suoAnGang + suoMingKe;
    }

    public int getTong() {
        return tongShunZi + tongMingGang + tongAnGang + tongMingKe;
    }

    public int getFeng() {
        return fengMingGang + fengAnGang + fengMingKe;
    }

    public int getYi() {
        return yiMingGang + yiAnGang + yiMingKe;
    }

    public int getZi() {
        return getFeng() + getYi();
    }


    public static ShowEswnzfbx build(List<MajGroup> groups) {
        ShowEswnzfbx.ShowEswnzfbxBuilder builder = ShowEswnzfbx.builder();
        builder.groupNum(groups.size());
        int wanShunZi = 0;
        int tongShunZi = 0;
        int suoShunZi = 0;

        int wanMingKe = 0;
        int tongMingKe = 0;
        int suoMingKe = 0;
        int fengMingKe = 0;
        int yiMingKe = 0;

        int wanAnGang = 0;
        int tongAnGang = 0;
        int suoAnGang = 0;
        int fengAnGang = 0;
        int yiAnGang = 0;

        int wanMingGang = 0;
        int tongMingGang = 0;
        int suoMingGang = 0;
        int fengMingGang = 0;
        int yiMingGang = 0;
        if (groups.size() > 0) {
            for (MajGroup group : groups) {
                Maj maj = group.getMajs().get(0);
                switch (group.getType()) {
                    case MajGroup.SHUN_ZI: {
                        if (maj.getType() == Maj.WAN) {
                            wanShunZi++;
                        }
                        if (maj.getType() == Maj.TONG) {
                            tongShunZi++;
                        }
                        if (maj.getType() == Maj.SUO) {
                            suoShunZi++;
                        }
                        break;
                    }
                    case MajGroup.MING_KE: {
                        if (maj.getType() == Maj.WAN) {
                            wanMingKe++;
                        }
                        if (maj.getType() == Maj.TONG) {
                            tongMingKe++;
                        }
                        if (maj.getType() == Maj.SUO) {
                            suoMingKe++;
                        }
                        if (maj.getType() == Maj.ZI) {
                            if (maj.getContent() <= 3) {
                                fengMingGang++;
                            } else {
                                yiMingKe++;
                            }
                        }
                        break;
                    }
                    case MajGroup.AN_GANG: {
                        if (maj.getType() == Maj.WAN) {
                            wanAnGang++;
                        }
                        if (maj.getType() == Maj.TONG) {
                            tongAnGang++;
                        }
                        if (maj.getType() == Maj.SUO) {
                            suoAnGang++;
                        }
                        if (maj.getType() == Maj.ZI) {
                            if (maj.getContent() <= 3) {
                                fengAnGang++;
                            } else {
                                yiAnGang++;
                            }
                        }
                        break;
                    }
                    case MajGroup.MING_GANG: {
                        if (maj.getType() == Maj.WAN) {
                            wanMingGang++;
                        }
                        if (maj.getType() == Maj.TONG) {
                            tongMingGang++;
                        }
                        if (maj.getType() == Maj.SUO) {
                            suoMingGang++;
                        }
                        if (maj.getType() == Maj.ZI) {
                            if (maj.getContent() <= 3) {
                                fengMingGang++;
                            } else {
                                yiMingGang++;
                            }
                        }
                        break;
                    }
                    default: {
                    }
                }
                builder.wanShunZi(wanShunZi).wanMingKe(wanMingKe).wanAnGang(wanAnGang).wanMingGang(wanMingGang);
                builder.tongShunZi(tongShunZi).tongMingKe(tongMingKe).tongAnGang(tongAnGang).tongMingGang(tongMingGang);
                builder.suoShunZi(suoShunZi).suoMingKe(suoMingKe).suoAnGang(suoAnGang).suoMingGang(suoMingGang);
                builder.fengMingKe(fengMingKe).fengAnGang(fengAnGang).fengMingGang(fengMingGang);
                builder.yiMingKe(yiMingKe).yiAnGang(yiAnGang).yiMingGang(yiMingGang);
            }
        }
        return builder.build();
    }

}
