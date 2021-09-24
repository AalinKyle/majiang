package com.example.majiang;

import com.example.majiang.p.Player;
import lombok.Data;

import java.util.*;

@Data
public class MajTable {

    /**
     * 发牌
     */
    public void deal() {
        for (int i = 0; i < 3; i++) {
            for (Player<Maj, MajGroup> player : players) {
                for (int j = 0; j < 4; j++) {
                    player.touch(touch());
                }
            }
        }
        for (Player<Maj, MajGroup> player : players) {
            player.touch(touch());
        }
    }


    private List<Maj> pool = new ArrayList<>(136);
    private int canTouch = 122;
    private int baoPaiNum = 5;
    private int liBaoPaiNum = 5;
    private int gangNum = 4;
    private List<Maj> canTouchPool = new ArrayList<>(122);
    private List<Maj> gangs = new ArrayList<>(4);
    private List<Maj> baoPais = new ArrayList<>(5);
    private List<Maj> liBaoPais = new ArrayList<>(5);
    private List<Player<Maj, MajGroup>> players;
    private Maj current;

    private int baopaiIndex = 0;
    private int gangIndex = 0;

    public void addPlayer(Player<Maj, MajGroup> player) {
        players.add(player);
    }

    public void addPlayers(List<Player<Maj, MajGroup>> players) {
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

    private int singleNum = 4;

    private void init() {
        players = new ArrayList<>();
        for (int i = 0; i < singleNum; i++) {
            for (int j = 0; j < Maj.contents.length; j++) {
                pool.add(new Maj(Maj.WAN, j));
                pool.add(new Maj(Maj.TONG, j));
                pool.add(new Maj(Maj.SUO, j));
            }
            for (int j = 0; j < Maj.zi.length; j++) {
                pool.add(new Maj(Maj.ZI, j));
            }
        }
    }

    public Maj touchBaoPai() {
        if (baopaiIndex < baoPais.size()) {
            return baoPais.get(baopaiIndex++);
        } else {
            throw new NullPointerException("baopai pool is empty");
        }
    }

    public Maj touchGang() {
        if (gangIndex < gangs.size()) {
            return gangs.get(gangIndex++);
        } else {
            throw new NullPointerException("gangs pool is empty");
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
            throw new NullPointerException("pool is empty");
        }
    }

    public boolean canTouch() {
        return canTouchPool.size() > 0;
    }


}
