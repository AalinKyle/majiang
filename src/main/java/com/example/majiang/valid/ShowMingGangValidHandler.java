package com.example.majiang.valid;

import com.example.majiang.*;
import com.example.majiang.p.Player;

import java.util.Arrays;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:38
 */
public class ShowMingGangValidHandler extends BaseGangValidHandler {

    private boolean showContain(List<MajGroup> groups, MajGroup group) {
        for (MajGroup m : groups) {
            if (m.equals(group)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public GangRecord validGang(Player<Maj, MajGroup> player, GameInfo gameInfo) {
        Maj current = gameInfo.getCurrentMaj();
        List<MajGroup> show = player.getShow();
        MajGroup majGroup = new MajGroup(MajGroup.MING_KE, Arrays.asList(new GroupMaj(current), new GroupMaj(current), new GroupMaj(current)));
        if (showContain(show, majGroup)) {
            return new GangRecord(true, true,
                    new FuluObj(
                            new MajGroup(MajGroup.MING_GANG, getGroupMajs(current, true)),
                            Arrays.asList(current),
                            Arrays.asList(majGroup)));
        } else {
            return new GangRecord(false);
        }

    }
}
