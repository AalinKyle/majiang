package com.example.majiang;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
public class User {
    private String name;
    private AtomicInteger point = new AtomicInteger();

    public User(String name) {
        this(name, 0);
    }

    public User(String name, int point) {
        this.name = name;
        this.point = new AtomicInteger(point);
    }
}
