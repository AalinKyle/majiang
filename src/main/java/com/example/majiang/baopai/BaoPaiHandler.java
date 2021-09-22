package com.example.majiang.baopai;

import com.example.majiang.Maj;
import com.example.majiang.MajTable;

import java.util.List;

public interface BaoPaiHandler {
    public void init(MajTable table);

    public Maj touchOpeningBaoPai();

    public Maj touchGangBaoPai();

    public void putBaoPai(Maj maj);

    public List<Maj> getBaoPais();
}
