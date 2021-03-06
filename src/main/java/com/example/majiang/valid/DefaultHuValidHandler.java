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
        List<MajGroup> handMajGroup = null;
        for (PreHuValid preHuValid : valids.keySet()) {
            HandMajDistribution distribution = new HandMajDistribution(majs);
            List<HuValid> huValids = valids.get(preHuValid);
            PreValidRes preValidRes = preHuValid.valid0(distribution, show, discard, gameInfo);
            if (preValidRes != null && preValidRes.isValid()) {
                List<MajGroup> handMajGroups = preValidRes.getHandMajGroups();
                handMajGroup = handMajGroups;
                for (HuValid v : huValids) {
                    distribution = new HandMajDistribution(majs);

                    Fan fan = v.valid(distribution, show, discard, handMajGroups, gameInfo);
                    if (fan != null) {
                        fans.add(fan);
                    }
                }
            }
        }
        boolean hu = false;
        boolean isYiMan = false;
        List<Fan> resFans = new ArrayList<>();
        for (int i = 0; i < fans.size(); i++) {
            Fan fan = fans.get(i);
            if (fan.isCanHu()) {
                hu = true;
            }
            /**
             * ????????????????????????????????????????????????????????????????????????????????????
             */
            if (fan.isYiMan()) {
                if (!isYiMan) {
                    resFans.clear();
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
        HuMaj huMaj = new HuMaj(majs, show, discard, gameInfo.getCurrentMaj(), gameInfo, handMajGroup);
        if (fans.size() > 0) {
            return new HuRecord()
                    .setYiMan(isYiMan)
                    .setHu(hu)
                    .setFans(resFans)
                    .setHuMaj(huMaj);
        } else {
            return null;
        }
    }
}
