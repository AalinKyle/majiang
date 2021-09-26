package com.example.majiang;

import com.example.majiang.ex.NoPlayersException;
import com.example.majiang.ex.PoolIsEmptyException;
import com.example.majiang.p.BasePlayer;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kyle
 */
@Data
public class MajTable {

    /**
     * 发牌
     */
    public void deal() {
        if (players.size() == 0) {
            throw new NoPlayersException();
        }
        /**
         * 每人先摸三组
         */
        for (int i = 0; i < 3; i++) {
            for (BasePlayer player : players) {
                for (int j = 0; j < 4; j++) {
                    player.touch(touch());
                }
            }
        }
        for (BasePlayer player : players) {
            player.touch(touch());
        }
    }

    private MajsCase majsCase = new DefaultMajsCase();
    private List<Maj> pool = new ArrayList<>(136);
    private int canTouch = 122;
    private int baoPaiNum = 5;
    private int liBaoPaiNum = 5;
    private int gangNum = 4;
    private List<Maj> canTouchPool = new ArrayList<>(122);
    private List<Maj> gangs = new ArrayList<>(4);
    private List<Maj> baoPais = new ArrayList<>(5);
    private List<Maj> liBaoPais = new ArrayList<>(5);
    private List<BasePlayer> players;

    private int baopaiIndex = 0;
    private int gangIndex = 0;

    public void addPlayer(BasePlayer player) {
        players.add(player);
    }

    public void addPlayers(List<BasePlayer> players) {
        this.players.addAll(players);
    }

    public void shuffle() {
        Collections.shuffle(pool);
    }

    /**
     * 摆好牌
     */

    public void putMajs() {
        for (int i = 0; i < canTouch; i++) {
            canTouchPool.add(pool.get(i));
        }
        int index = pool.size();
        int i = 0;
        while (i++ < gangNum) {
            gangs.add(pool.get(index -= 1));
        }
        i = 0;
        while (i++ < baoPaiNum) {
            baoPais.add(pool.get(index -= 1));
            liBaoPais.add(pool.get(index -= 1));
        }
    }

    public MajTable() {
        init();
    }


    private void init() {
        players = new ArrayList<>();
        pool.addAll(majsCase.getMajs());
    }

    public Maj touchBaoPai() {
        if (baopaiIndex < baoPais.size()) {
            return baoPais.get(baopaiIndex++);
        } else {
            throw new PoolIsEmptyException("baopai pool is empty");
        }
    }

    public Maj touchGang() {
        if (gangIndex < gangs.size()) {
            return gangs.get(gangIndex++);
        } else {
            throw new PoolIsEmptyException("gangs pool is empty");
        }
    }

    public List<Maj> returnLiBaoPais() {
        List<Maj> lis = new ArrayList<>();
        for (int i = 0; i < baopaiIndex; i++) {
            lis.add(liBaoPais.get(i));
        }
        return lis;
    }

    public Maj touch() {
        if (canTouch()) {
            return canTouchPool.remove(0);
        } else {
            throw new PoolIsEmptyException("pool is empty");
        }
    }

    public boolean canTouch() {
        return canTouchPool.size() > 0;
    }


}
