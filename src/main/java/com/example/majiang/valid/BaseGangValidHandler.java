package com.example.majiang.valid;

import com.example.majiang.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:38
 */
public abstract class BaseGangValidHandler implements GangValidHandler {
    protected final int canGangNum = 3;

    protected List<GroupMaj> getGroupMajs(Maj current, boolean mangGang) {
        List<GroupMaj> groups = new ArrayList<>();
        int type = current.getType();
        int content = current.getContent();
        if (mangGang) {
            for (int i = 0; i < canGangNum + 1; i++) {
                //如果是明杠的话，则第一张牌就当做是杠来的
                groups.add(new GroupMaj(type, content, i == 0));
            }
        } else {
            for (int i = 0; i < canGangNum + 1; i++) {
                groups.add(new GroupMaj(type, content));
            }
        }
        return groups;
    }

    protected List<Maj> getNeedRemove(Maj current, boolean mingGang) {
        List<Maj> needRemove = new ArrayList<>();
        int type = current.getType();
        int content = current.getContent();
        /**
         * 如果是暗杠，需要4张全去了
         */
        for (int i = 0; i < canGangNum + (mingGang ? 0 : 1); i++) {
            //如果是明杠的话，则第一张牌就当做是杠来的
            needRemove.add(new Maj(type, content));
        }
        return needRemove;
    }


}
