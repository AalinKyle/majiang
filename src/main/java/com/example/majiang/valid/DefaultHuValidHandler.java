package com.example.majiang.valid;

import com.example.majiang.*;

import java.util.*;

/**
 * @Author kyle
 * @create 2021/9/22 14:47
 */
public class DefaultHuValidHandler implements HuValidHandler {
    private Map<PreHuValid, List<HuValid>> valids;
    private Map<Class, PreHuValid> classMap;

    public DefaultHuValidHandler(Hus hus) {
        if (hus == null) {
            throw new NullPointerException();
        } else {
            valids = new HashMap<>();
            classMap = new HashMap<>();
            List<HuValid> huValids = hus.getHus();
            for (int i = 0; i < huValids.size(); i++) {
                HuValid huValid = huValids.get(i);
                Hu hu = huValid.getClass().getAnnotation(Hu.class);
                Class preClazz = NoValidPreHuValid.class;
                if (hu != null) {
                    preClazz = hu.preClazz();
                }
                try {
                    PreHuValid preHuValid = classMap.getOrDefault(preClazz, (PreHuValid) preClazz.newInstance());
                    classMap.put(preClazz, preHuValid);
                    List<HuValid> list = valids.getOrDefault(preHuValid, new ArrayList<>());
                    list.add(huValid);
                    valids.put(preHuValid, list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public HuRecord validFan(List<Maj> majs, List<MajGroup> show, List<Maj> discard, GameInfo gameInfo) {
        List<Fan> fans = new ArrayList<>();

        for (PreHuValid preHuValid : valids.keySet()) {
            HandMajDistribution distribution = new HandMajDistribution(majs);
            List<HuValid> huValids = valids.get(preHuValid);
            PreValidRes preValidRes = preHuValid.valid0(distribution, show, discard, gameInfo);
            if (preValidRes != null && preValidRes.isValid()) {
                for (HuValid v : huValids) {
                    distribution = new HandMajDistribution(majs);
                    Fan fan = v.valid(distribution, show, discard, preValidRes.getHandMajGroups(), gameInfo);
                    fans.add(fan);
                }
            }
        }
        boolean hu = false;
        boolean isYiMan = false;
        List<Fan> resFans = new ArrayList<>();
        for (Fan fan : fans) {
            if (fan != null) {
                if (fan.isCanHu()) {
                    hu = true;
                }
                /**
                 * 当胡的牌出现役满牌型，就不要计算其他的小胡了，只统计役满
                 */
                if (fan.isYiMan()) {
                    if (!isYiMan) {
                        fans.clear();
                    }
                    isYiMan = true;
                }
                if (isYiMan) {
                    if (fan.isYiMan()) {
                        resFans.add(fan);
                    }
                } else {
                    resFans.add(fan);
                }
            }
        }
        if (fans.size() > 0) {
            return new HuRecord().setYiMan(isYiMan).setHu(hu).setHuTime(new Date()).setFans(resFans).setHuMaj(new HuMaj(majs, show, discard));
        } else {
            return null;
        }
    }
}
