package com.example.majiang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameInfo {
    /**
     * 场风
     */
    private int changFeng;
    /**
     * 自风
     */
    private int ziFeng;
    private List<Maj> baoPai;
    private Map<Integer, PlayerTableInfo> playerTableInfos;
}
