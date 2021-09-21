package com.example.majiang.p;

import com.example.majiang.Maj;
import com.example.majiang.User;

import java.util.Comparator;
import java.util.List;

public class RemoveYaoJiuPlayer extends Player<Maj> {

    public RemoveYaoJiuPlayer() {
    }

    public RemoveYaoJiuPlayer(User user, Comparator<Maj> sort, boolean enableLog) {
        super(user, sort, enableLog);
    }

    public RemoveYaoJiuPlayer(User user, Comparator<Maj> sort) {
        super(user, sort);
    }

    @Override
    public Maj play() {
        List<Maj> hand = getHand();
        for (int i = 0; i < hand.size(); i++) {
            Maj t = hand.get(i);
            if (t.getType() == Maj.ZI) {
                return hand.remove(i);
            }
            if (t.getContent() == 0 || t.getContent() == 8) return hand.remove(i);
        }
        return super.play();
    }
}
