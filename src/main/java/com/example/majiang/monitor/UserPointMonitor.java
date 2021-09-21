package com.example.majiang.monitor;

import com.example.majiang.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class UserPointMonitor extends MajGameMonitor {
    private List<User> users;

    public UserPointMonitor(List<User> users) {
        this(users, 30000);
    }

    public UserPointMonitor(List<User> users, int sleep) {
        this.users = users;
        this.sleep = sleep;
    }

    @Override
    protected void doLog() {
        super.doLog();
        for (User user : users) {
            log.info("选手{} 目前得分{}", user.getName(), user.getPoint());
        }
    }

    @Override
    protected int getSleep() {
        return sleep;
    }
}
