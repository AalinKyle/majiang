package com.example;

import com.example.majiang.*;
import com.example.majiang.monitor.UserPointMonitor;
import com.example.majiang.p.*;
import com.example.majiang.valid.MyHus;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MajiangApp {

    public static void main(String[] args) {
        User user1 = new User("1去字1", 0);
        User user2 = new User("2去字2", 0);
        User user3 = new User("3去字3", 0);
        User user4 = new User("4一色4", 0);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                List<Player<Maj, MajGroup>> players = new ArrayList<>();
                players.add(new RemoveZiPlayer(user1, new MajSort()));
                players.add(new RemoveZiPlayer(user2, new MajSort()));
                players.add(new RemoveZiPlayer(user3, new MajSort()));
                players.add(new YiSePlayer(user4, new MajSort()));
                MajGame game = new MajGame(new MyHus());
                while (true) {
                    game.play(players);
                    game.clear();
                }
            }).start();
        }
        new Thread(new UserPointMonitor(Arrays.asList(user1, user2, user3, user4))).start();
    }

    private static Player<Maj, MajGroup> buildPlayer(User user) {
        return new BasePlayer(user, new MajSort());
    }
}
