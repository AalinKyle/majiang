package com.example.majiang.p;

import com.example.majiang.Maj;

import java.util.Comparator;
import java.util.List;

public class RemoveZiJiuPlayer extends Player<Maj> {
    public RemoveZiJiuPlayer() {
    }

    public RemoveZiJiuPlayer(String name, Comparator<Maj> sort, boolean enableLog) {
        super(name, sort, enableLog);
    }

    public RemoveZiJiuPlayer(String name, Comparator<Maj> sort) {
        super(name, sort);
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
