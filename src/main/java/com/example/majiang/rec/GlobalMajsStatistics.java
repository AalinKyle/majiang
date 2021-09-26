package com.example.majiang.rec;

import com.example.majiang.HuMaj;
import com.example.majiang.HuRecord;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author kyle
 * @create 2021/9/26 11:39
 */
public class GlobalMajsStatistics {
    public static Map<String, List<HuRecord>> RECORD = new ConcurrentHashMap<>();
    public static Map<String, List<HuMaj>> PLAYER_REC = new ConcurrentHashMap<>();
    public static HuRecord MAX;
}
