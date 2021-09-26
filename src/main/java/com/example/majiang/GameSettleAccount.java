package com.example.majiang;

import com.example.majiang.p.BasePlayer;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author kyle
 * @create 2021/9/24 10:43
 */
@Data
public class GameSettleAccount {

    public interface Result {
        int HU = 1;
        int LIU_JU_HUANGPAI = 2;
        int LIU_JU_SIGANG = 3;
        int LIU_JU_SIFENG = 4;
    }

    public interface Type {
        int NO_HU = 0;
        int ZI_MO = 1;
        int FANG_PAO = 2;
    }

    private int changFeng;
    /**
     * 庄家
     */
    private int dealer;
    private Date recordTime;
    /**
     * 结果
     * 胡/流局(荒牌/四杠/四风...)/...
     */
    private int result;
    /**
     * 自摸，放炮
     */
    private int winType;
    private int roundNum;
    private Map<Integer, BasePlayer> noMap;
    private List<WinnerRecord> winnerRecords;
    private List<Maj> pool;
    private List<Maj> baoPais;
    private List<Maj> liBaoPais;

}
