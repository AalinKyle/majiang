package com.example.majiang.p;

import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.User;
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


    private User user;

    public void addPoint(int n) {
        user.getPoint().addAndGet(n);
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

    public Player(User user, Comparator<T> sort, boolean enableLog) {
        this.name = user.getName();
        hand = new LinkedList<>();
        show = new LinkedList<>();
        discard = new LinkedList<>();
        this.sort = sort;
        this.enableLog = enableLog;
        this.user = user;
    }

    public Player(User user, Comparator<T> sort) {
        this(user, sort, false);
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
