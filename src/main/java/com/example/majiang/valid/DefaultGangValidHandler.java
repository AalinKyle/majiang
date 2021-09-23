package com.example.majiang.valid;

import com.example.majiang.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:38
 */
public abstract class DefaultGangValidHandler implements GangValidHandler {

    protected abstract boolean isMangGang();

    @Override
    public GangRecord validGang(List<Maj> hand, GameInfo gameInfo) {
        int canGangNum = 3;
        Maj current = gameInfo.getCurrentMaj();
        HandMajDistribution hmd = new HandMajDistribution(hand);
        int type = current.getType();
        int content = current.getContent();
        List<GroupMaj> groups = new ArrayList<>();
        if (isMangGang()) {
            for (int i = 0; i < canGangNum + 1; i++) {
                //如果是明杠的话，则第一张牌就当做是杠来的
                groups.add(new GroupMaj(type, content, i == 0));
            }
        } else {
            for (int i = 0; i < canGangNum + 1; i++) {
                groups.add(new GroupMaj(type, content));
            }
        }
        List<Maj> needRemove = new ArrayList<>();
        for (int i = 0; i < canGangNum; i++) {
            //如果是明杠的话，则第一张牌就当做是杠来的
            needRemove.add(new Maj(type, content));
        }

        switch (type) {
            case Maj.WAN: {
                int[] wan = hmd.getWan();
                if (wan[content] == canGangNum) {
                    return new GangRecord(true, isMangGang(), new FuluRecord(new MajGroup(isMangGang() ? MajGroup.MING_KE : MajGroup.AN_GANG, groups), needRemove));
                }
                break;
            }
            case Maj.TONG: {
                int[] tong = hmd.getTong();
                if (tong[content] == canGangNum) {
                    return new GangRecord(true, isMangGang(), new FuluRecord(new MajGroup(isMangGang() ? MajGroup.MING_KE : MajGroup.AN_GANG, groups), needRemove));
                }
                break;
            }
            case Maj.SUO: {
                int[] suo = hmd.getSuo();
                if (suo[content] == canGangNum) {
                    return new GangRecord(true, isMangGang(), new FuluRecord(new MajGroup(isMangGang() ? MajGroup.MING_KE : MajGroup.AN_GANG, groups), needRemove));
                }
                break;
            }
            case Maj.ZI: {
                int[] zi = hmd.getZi();
                if (zi[content] == canGangNum) {
                    return new GangRecord(true, isMangGang(), new FuluRecord(new MajGroup(isMangGang() ? MajGroup.MING_KE : MajGroup.AN_GANG, groups), needRemove));
                }
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        return new GangRecord(false);
    }
}
