package com.example.majiang.valid;

import com.example.majiang.GameInfo;
import com.example.majiang.HandMajDistribution;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.p.BasePlayer;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:38
 */
public class GetMajHandMingGangValidHandler extends BaseGangValidHandler {

    @Override
    public GangRecord validGang(BasePlayer player, GameInfo gameInfo) {
        List<Maj> hand = player.getHand();
        HandMajDistribution hmd = new HandMajDistribution(hand);
        Maj current = gameInfo.getCurrentMaj();
        int type = current.getType();
        int content = current.getContent();
        switch (type) {
            case Maj.WAN: {
                int[] wan = hmd.getWan();
                /**
                 *明杠 手牌里有3张
                 */
                if (wan[content] == canGangNum) {
                    return new GangRecord(true, true, new FuluObj(new MajGroup(MajGroup.AN_GANG, getGroupMajs(current, true)), getNeedRemove(current, true)));
                }
                break;
            }
            case Maj.TONG: {
                int[] tong = hmd.getTong();
                if (tong[content] == canGangNum) {
                    return new GangRecord(true, true, new FuluObj(new MajGroup(MajGroup.MING_GANG, getGroupMajs(current, true)), getNeedRemove(current, true)));
                }
                break;
            }
            case Maj.SUO: {
                int[] suo = hmd.getSuo();
                if (suo[content] == canGangNum) {
                    return new GangRecord(true, true, new FuluObj(new MajGroup(MajGroup.MING_GANG, getGroupMajs(current, true)), getNeedRemove(current, true)));
                }
                break;
            }
            case Maj.ZI: {
                int[] zi = hmd.getZi();
                if (zi[content] == canGangNum) {
                    return new GangRecord(true, true, new FuluObj(new MajGroup(MajGroup.MING_GANG, getGroupMajs(current, true)), getNeedRemove(current, true)));
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
