package com.example.majiang.valid;

import com.example.majiang.GameInfo;
import com.example.majiang.GroupMaj;
import com.example.majiang.Maj;
import com.example.majiang.MajGroup;

import java.util.Arrays;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:39
 */
public class DefaultPengValidHandler implements PengValidHandler {
    @Override
    public PengRecord validPeng(List<Maj> hand, GameInfo gameInfo) {
        Maj currentMaj = gameInfo.getCurrentMaj();
        int num = 0;
        for (Maj maj : hand) {
            if (maj.equals(currentMaj)) {
                num++;
            }
            if (num == 2) {
                List<GroupMaj> groupMajs = Arrays.asList(new GroupMaj(maj, true), new GroupMaj(maj), new GroupMaj(maj));
                List<Maj> discard = Arrays.asList(new GroupMaj(maj), new GroupMaj(maj));
                return new PengRecord(true, new FuluObj(new MajGroup(MajGroup.MING_KE, groupMajs), discard));
            }
        }
        return new PengRecord(false);
    }
}
