package com.example.majiang.monitor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public abstract class BaseMonitor implements Runnable {

    protected abstract void doLog();

    protected abstract int getSleep();

    @Override
    public void run() {
        while (true) {
            int sleep = getSleep();
            doLog();
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected static interface LogInfo {
        public void l(Logger logger);
    }
}
