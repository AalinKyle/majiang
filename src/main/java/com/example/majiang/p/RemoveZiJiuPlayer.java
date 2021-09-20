package com.example.majiang.p;

import com.example.majiang.Maj;
import com.example.majiang.User;

import java.util.Comparator;
import java.util.List;

public class RemoveZiJiuPlayer extends Player<Maj> {
    public RemoveZiJiuPlayer() {
    }

    public RemoveZiJiuPlayer(User user, Comparator<Maj> sort, boolean enableLog) {
        super(user, sort, enableLog);
    }

    public RemoveZiJiuPlayer(User user, Comparator<Maj> sort) {
        super(user, sort);
    }

    @Override
    public Maj play() {
        List<Maj> hand = getHand();
        for (int i = 0; i < hand.size(); i++) {
            Maj t = hand.get(i);
            if (t.getType() == 3) {
                return hand.remove(i);
            }
        }
        return super.play();
    }
}
