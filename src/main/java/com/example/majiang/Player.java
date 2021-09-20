package com.example.majiang;

import lombok.Data;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Data
public class Player<T extends Maj> {

    private List<T> hand;
    private List<MajGroup> show;
    private List<T> discard;

    private Comparator<T> sort;

    public Player(Comparator<T> sort) {
        hand = new LinkedList<>();
        show = new LinkedList<>();
        discard = new LinkedList<>();
        this.sort = sort;
    }

    public void touch(T maj) {
        touch(maj, sort);
    }

    public void touch(T maj, Comparator<T> sort) {
        hand.add(maj);
        hand.sort(sort);
    }

    private Random random = new Random();

    public T play() {
        T remove = hand.remove(random.nextInt(hand.size()));
        discard.add(remove);
        return remove;
    }

}
