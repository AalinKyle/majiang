package com.example.majiang.valid;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:44
 */
@Data
@NoArgsConstructor
public class GangRecord {
    protected boolean gang;
    protected boolean mingGang;
    private List<FuluRecord> records;

    public GangRecord(boolean gang) {
        this.gang = gang;
    }

    public GangRecord(boolean gang, boolean mingGang, FuluRecord... records) {
        this.gang = gang;
        this.mingGang = mingGang;
        this.records = Arrays.asList(records);
    }

    public GangRecord(boolean gang, boolean mingGang, List<FuluRecord> records) {
        this.gang = gang;
        this.mingGang = mingGang;
        this.records = records;
    }
}
