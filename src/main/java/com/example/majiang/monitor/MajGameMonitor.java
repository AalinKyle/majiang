package com.example.majiang.monitor;

import com.example.majiang.MajGame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MajGameMonitor extends BaseMonitor {
    private int pre = 0;
    private int sleep = 5000;
    private int showSleep = 1000;

    @Override
    protected void doLog() {
        int gameNum = MajGame.gameNum.get();
        log.info("当前速度为{}局/{}ms", (gameNum - pre) / (sleep / showSleep), showSleep);
        pre = gameNum;
    }

    @Override
    protected int getSleep() {
        return sleep;
    }
}
