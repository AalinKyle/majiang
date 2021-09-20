package com.example.majiang.monitor;

import com.example.majiang.Maj;
import com.example.majiang.p.Player;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class PlayerPointMonitor extends BaseMonitor {
    private int sleep = 5000;
    private List<Player<Maj>> players;

    public PlayerPointMonitor(List<Player<Maj>> players) {
        this.players = players;
    }

    @Override
    protected void doLog() {
        for (Player<Maj> player : players) {
            log.info("选手{} 目前得分{}", player.getName(), player.getPoint());
        }
    }

    @Override
    protected int getSleep() {
        return sleep;
    }
}
