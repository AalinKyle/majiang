package com.example.majiang.p;

import com.example.majiang.GameInfo;
import com.example.majiang.Maj;
import com.example.majiang.User;
import com.example.majiang.valid.ChiRecord;
import com.example.majiang.valid.FuluObj;
import com.example.majiang.valid.GangRecord;
import com.example.majiang.valid.PengRecord;

import java.util.Comparator;
import java.util.List;

/**
 * 不吃碰杠
 */
public class NoCPGPlayer extends BasePlayer {
    public NoCPGPlayer() {
    }

    public NoCPGPlayer(User user, Comparator<Maj> sort, boolean enableLog) {
        super(user, sort, enableLog);
    }

    public NoCPGPlayer(User user, Comparator<Maj> sort) {
        super(user, sort);
    }

    @Override
    public Maj play(GameInfo gameInfo) {
        return super.play(gameInfo);
    }

    @Override
    public FuluObj chooseGang(List<GangRecord> records, GameInfo gameInfo) {
        return null;
    }

    @Override
    public FuluObj choosePeng(PengRecord record, GameInfo gameInfo) {
        return null;
    }

    @Override
    public FuluObj chooseChi(ChiRecord record, GameInfo gameInfo) {
        return null;
    }


}
