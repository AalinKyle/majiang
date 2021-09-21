package com.example.majiang.baopai;

import com.example.majiang.Maj;
import com.example.majiang.MajTable;

import java.util.ArrayList;
import java.util.List;

public class DefaultBaoPaiHandler implements BaoPaiHandler {
    private List<Maj> baoPai;
    private MajTable table;

    @Override
    public void init(MajTable table) {
        this.baoPai = new ArrayList<>();
        this.table = table;
    }

    @Override
    public Maj touchOpeningBaoPai() {
        Maj touch = table.touch();
        return next(touch);
    }

    @Override
    public Maj touchGangBaoPai() {
        Maj touch = table.touch();
        return next(touch);
    }

    private Maj next(Maj touch) {
        int type = touch.getType();
        int content = touch.getContent();
        if (type == Maj.ZI) {
            return new Maj(type, (content + 1) % 7);
        } else {
            return new Maj(type, (content + 1) % 9);
        }
    }

    @Override
    public void putBaoPai(Maj maj) {
        baoPai.add(maj);
    }


    @Override
    public List<Maj> getBaoPai() {
        return baoPai;
    }


}
