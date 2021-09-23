package com.example.majiang;

import lombok.Data;

/**
 * @Author kyle
 * @create 2021/9/22 17:04
 */
@Data
public class GroupMaj extends Maj {
    public GroupMaj(int type, int content, boolean outGet) {
        super(type, content);
        this.outGet = outGet;
    }

    public GroupMaj(Maj maj) {
        this(maj.getType(), maj.getContent());
    }

    public GroupMaj(Maj maj, boolean outGet) {
        this(maj.getType(), maj.getContent(), outGet);
    }

    public GroupMaj(int type, int content) {
        this(type, content, false);
    }

    private boolean outGet;
}
