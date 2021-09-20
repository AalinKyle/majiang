package com.example.majiang;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Data
@Slf4j
@NoArgsConstructor
public class Player<T extends Maj> {
    private String name;
    private boolean enableLog;
    private List<T> hand;
    private List<MajGroup> show;
    private List<T> discard;

    private Comparator<T> sort;

    public Player(String name, Comparator<T> sort, boolean enableLog) {
        this.name = name;
        hand = new LinkedList<>();
        show = new LinkedList<>();
        discard = new LinkedList<>();
        this.sort = sort;
        this.enableLog = enableLog;
    }

    public Player(String name, Comparator<T> sort) {
        this(name, sort, false);
    }

    public void touch(T maj) {
        touch(maj, sort);
    }

    public void touch(T maj, Comparator<T> sort) {
        info("{}=>摸了{}", name, maj);
        hand.add(maj);
        hand.sort(sort);
    }

    private Random random = new Random();

    public T play() {
        T play = hand.remove(random.nextInt(hand.size()));
        info("{}=>打出了{}", name, play);
        return play;
    }

    public void addDiscard(T t) {
        discard.add(t);
    }

    private void info(String str, Object... arg) {
        if (enableLog) {
            log.info(str, arg);
        }
    }
}
