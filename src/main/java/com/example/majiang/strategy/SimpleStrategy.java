package com.example.majiang.strategy;

import com.example.majiang.GameInfo;
import com.example.majiang.HuRecord;
import com.example.majiang.Maj;
import com.example.majiang.valid.ChiRecord;
import com.example.majiang.valid.FuluObj;
import com.example.majiang.valid.GangRecord;
import com.example.majiang.valid.PengRecord;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/23 11:30
 */
public class SimpleStrategy implements MajStrategy<Maj> {
    @Override
    public Maj play(GameInfo gameInfo) {
        return null;
    }

    @Override
    public FuluObj chooseGang(List<GangRecord> record, GameInfo gameInfo) {
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

    @Override
    public boolean chooseHu(HuRecord huRecord, GameInfo touchGameInfo) {
        return true;
    }
}
