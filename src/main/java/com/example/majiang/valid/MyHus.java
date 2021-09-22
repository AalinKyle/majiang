package com.example.majiang.valid;

import com.example.majiang.valid.base.*;
import com.example.majiang.valid.spc.QiXiaoDuiHu;
import com.example.majiang.valid.spc.ShiSanYaoHu;

import java.util.ArrayList;
import java.util.List;

public class MyHus implements Hus {
    @Override
    public List<HuValid> getHus() {
        List<HuValid> huValids = new ArrayList<>();
        huValids.add(new BaoPaiHu());
        huValids.add(new BaiHu());
        huValids.add(new FaHu());
        huValids.add(new ZhongHu());
        huValids.add(new ZiFengHu());
        huValids.add(new ChangFengHu());
        huValids.add(new DuanYaoJiuHu());
        huValids.add(new HunQuanDaiYaoJiuHu());
        huValids.add(new YiQiGuanTongHu());
        huValids.add(new SanSeTongShunHu());
        huValids.add(new SanSeTongKeHu());
        huValids.add(new HunLaoTouHu());
        huValids.add(new QingLaoTouHu());
        huValids.add(new YiBeiKouHu());
        huValids.add(new ShiSanYaoHu());
        huValids.add(new QiXiaoDuiHu());
        huValids.add(new QingYiSeHu());
        huValids.add(new HunYiSeHu());
        huValids.add(new DuiDuiHu());
        huValids.add(new ZiYiSeHu());
        huValids.add(new DaSiXiHu());
        huValids.add(new XiaoSiXiHu());
        huValids.add(new DaSanYuanHu());
        huValids.add(new XiaoSanYuanHu());
        huValids.add(new SiAnKeHu());
        huValids.add(new QingLianBaoZhuHu());
        return huValids;
    }
}
