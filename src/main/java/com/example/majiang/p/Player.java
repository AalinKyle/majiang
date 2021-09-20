package com.example.majiang.p;

import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Slf4j
@NoArgsConstructor
public class Player<T extends Maj> {

    protected AtomicInteger point = new AtomicInteger();

    public void addPoint(int n) {
        point.addAndGet(n);
    }

    private String name;
    private boolean enableLog;
    private List<T> hand;
    private List<MajGroup> show;
    private List<T> discard;

    private Comparator<T> sort;

    public List<T> getHand() {
        return hand;
    }

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
//    public T play() {
//        for (int i = 0; i < hand.size(); i++) {
//            T t = hand.get(i);
//            if (t.getType() == 3) {
//                return hand.remove(i);
//            }
//            if (t.getContent() == 0 || t.getContent() == 8) return hand.remove(i);
//        }
//        T play = hand.remove(random.nextInt(hand.size()));
//        info("{}=>打出了{}", name, play);
//        return play;
//    }


    public void addDiscard(T t) {
        discard.add(t);
    }

    protected void info(String str, Object... arg) {
        if (enableLog) {
            log.info(str, arg);
        }
    }

    public void over() {
        hand = new LinkedList<>();
        show = new LinkedList<>();
        discard = new LinkedList<>();
    }
}
