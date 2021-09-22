package com.example.majiang.p;

import com.example.majiang.GameInfo;
import com.example.majiang.Maj;
import com.example.majiang.User;

import java.util.Comparator;
import java.util.List;

public class KeepYaoJiuPlayer extends BasePlayer {
    public KeepYaoJiuPlayer() {
    }

    public KeepYaoJiuPlayer(User user, Comparator<Maj> sort, boolean enableLog) {
        super(user, sort, enableLog);
    }

    public KeepYaoJiuPlayer(User user, Comparator<Maj> sort) {
        super(user, sort);
    }

    @Override
    public Maj play(GameInfo gameInfo) {
        List<Maj> hand = getHand();
        for (int i = 0; i < hand.size(); i++) {
            Maj t = hand.get(i);
            if (t.getType() != 3) {
                if (t.getContent() == 3 || t.getContent() == 4 || t.getContent() == 5) {
                    return hand.remove(i);
                }
            }
        }
        return super.play(gameInfo);
    }
}
