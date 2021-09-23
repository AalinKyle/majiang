package com.example.majiang.p;

import com.example.majiang.GameInfo;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.valid.ChiRecord;
import com.example.majiang.valid.FuluRecord;
import com.example.majiang.valid.GangRecord;
import com.example.majiang.valid.PengRecord;

import java.util.Comparator;
import java.util.List;

public interface Player<T extends Maj, U extends MajGroup> {

    public String getName();

    public List<U> getShow();

    public List<T> getDiscard();

    public void addPoint(int n);

    public void addShow(U group, List<T> discard);

    public List<T> getHand();

    public void touch(T maj);

    public void touch(T maj, Comparator<T> sort);

    public T play(GameInfo gameInfo);

    public void addDiscard(T t);

    public void over();

    public FuluRecord chooseGang(GangRecord record, GameInfo gameInfo);

    public FuluRecord choosePeng(PengRecord record, GameInfo gameInfo);

    public FuluRecord chooseChi(ChiRecord record, GameInfo gameInfo);
}
