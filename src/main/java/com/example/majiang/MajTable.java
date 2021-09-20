package com.example.majiang;

import com.example.majiang.valid.HuValid;
import lombok.Data;

import java.util.*;

@Data
public class MajTable {

    /**
     * 发牌
     */
    public void deal() {
        for (int i = 0; i < 3; i++) {
            for (Player<Maj> player : players) {
                for (int j = 0; j < 4; j++) {
                    player.touch(touch());
                }
            }
        }
        for (Player<Maj> player : players) {
                player.touch(touch());
        }
    }


    private List<Maj> pool = new ArrayList<>();

    private List<Player<Maj>> players;
    private Maj current;

    public void addPlayer(Player<Maj> player) {
        players.add(player);
    }

    public void addPlayers(List<Player<Maj>> players) {
        this.players.addAll(players);
    }

    public void shuffle() {
        Collections.shuffle(pool);
    }

    public MajTable() {
        init();
    }

    private void init() {
        players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                pool.add(new Maj(0, j));
                pool.add(new Maj(1, j));
                pool.add(new Maj(2, j));
            }
            for (int j = 0; j < 7; j++) {
                pool.add(new Maj(3, j));
            }
        }
    }

    public Maj touch() {
        if (pool.size() > 0) {
            return pool.remove(0);
        } else {
            throw new NullPointerException("pool is empty");
        }
    }
    public boolean canTouch() {
       return pool.size() > 0;
    }


}
