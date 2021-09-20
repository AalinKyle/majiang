package com.example.majiang;

import lombok.Builder;
import lombok.Data;

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

}
