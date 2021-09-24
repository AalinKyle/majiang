package com.example.majiang;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/24 10:43
 */
@Data
public class GameSettleAccount {
    private int changFeng;
    private int ziFeng;
    private Date huTime;
    /**
     * 结果
     * 胡/荒牌流局/...
     */
    private int result;
    /**
     * 自摸，放炮
     */
    private int winType;
    private List<WinnerRecord> winnerRecords;
    private List<Maj> pool;
    private List<Maj> baoPais;
    private List<Maj> liBaoPais;

}
