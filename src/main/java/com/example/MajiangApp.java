package com.example;

import com.example.majiang.*;
import com.example.majiang.monitor.MajGameMonitor;
import com.example.majiang.monitor.UserPointMonitor;
import com.example.majiang.p.Player;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MajiangApp {

    public static void main(String[] args) {
        User user1 = new User("1", 0);
        User user2 = new User("2", 0);
        User user3 = new User("3", 0);
        User user4 = new User("4", 0);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                List<Player<Maj>> players = new ArrayList<>();
                players.add(findPlayer(user1));
                players.add(findPlayer(user2));
                players.add(findPlayer(user3));
                players.add(findPlayer(user4));
                MajGame game = new MajGame(new MyHus());
                while (true) {
                    game.play(players);
                    game.clear();
                }
            }).start();
        }
//        new Thread(new MajGameMonitor()).start();
        new Thread(new UserPointMonitor(Arrays.asList(user1, user2, user3, user4))).start();
    }

    private static Player<Maj> findPlayer(User user) {
        return new Player<Maj>(user, new MajSort());
    }
}
