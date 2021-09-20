package com.example;

import com.example.majiang.*;
import com.example.majiang.monitor.MajGameMonitor;
import com.example.majiang.monitor.PlayerPointMonitor;
import com.example.majiang.p.KeepYaoJiuPlayer;
import com.example.majiang.p.Player;
import com.example.majiang.p.RemoveYaoJiuPlayer;
import com.example.majiang.p.RemoveZiJiuPlayer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MajiangApp {

    public static void main(String[] args) {
        List<Player<Maj>> players = findPlayer();
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                MajGame game = new MajGame(new MyHus());
                while (true) {
                    game.play(players);
                    game.clear();
                }
            }).start();
        }
        new Thread(new MajGameMonitor()).start();
        new Thread(new PlayerPointMonitor(players)).start();
    }

    private static List<Player<Maj>> findPlayer() {
        List<Player<Maj>> players = new ArrayList<>();
        Player<Maj> a = new RemoveYaoJiuPlayer("1", new MajSort());
        Player<Maj> b = new RemoveYaoJiuPlayer("2", new MajSort());
        Player<Maj> c = new RemoveYaoJiuPlayer("3", new MajSort());
        Player<Maj> d = new RemoveYaoJiuPlayer("4", new MajSort());
        players.add(a);
        players.add(b);
        players.add(c);
        players.add(d);
        return players;
    }
}
