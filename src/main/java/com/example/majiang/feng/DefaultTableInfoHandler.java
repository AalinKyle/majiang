package com.example.majiang.feng;

import java.util.List;

public class DefaultTableInfoHandler implements TableInfoHandler {
    @Override
    public int calZiFeng(int playerNo, int dealer) {
        return (playerNo + 4 - dealer) % 4;//0 3 1
    }

    @Override
    public MajTableInfo changeDealer(List<Integer> winnerNo, int currentDealer, int currentChangFeng, int playerNum) {
        if (winnerNo != null) {
            if (!winnerNo.contains(currentDealer)) {
                currentDealer++;
                if (currentDealer == playerNum) {
                    currentChangFeng = (currentChangFeng + 1) % 4;
                }
                currentDealer %= playerNum;
            }
        }
        return new MajTableInfo(currentDealer, currentChangFeng);
    }
}
