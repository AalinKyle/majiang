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

    /**
     * 自摸或者放炮
     */
    private HuRecord record;
    /**
     * name 就是userId这种
     */
    private String winnerName;
    private List<String> loserNames;

}
