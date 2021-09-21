package com.example.majiang.feng;

public interface TableInfoHandler {

    public int calZiFeng(int playNo, int dealer);

    public MajTableInfo changeDealer(Integer winnerNo, int currentDealer, int currentChangFeng, int playerNum);
}
