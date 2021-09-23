package com.example.majiang.feng;

import java.util.List;

public interface TableInfoHandler {

    public int calZiFeng(int playNo, int dealer);

    public MajTableInfo changeDealer(List<Integer> winnerNo, int currentDealer, int currentChangFeng, int playerNum);
}
