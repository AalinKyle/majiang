package com.example.majiang;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/24 13:43
 */
@Data
@Builder
public class WinnerRecord {
    interface Type {
        int ZI_MO = 1;
        int FANG_PAO = 2;
    }

    /**
     * 自摸或者放炮
     */
    private int type;
    private HuRecord record;
    /**
     * name 就是userId这种
     */
    private String winnerName;
    private List<String> loserNames;

}
