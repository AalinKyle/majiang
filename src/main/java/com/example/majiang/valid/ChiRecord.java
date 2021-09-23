package com.example.majiang.valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/23 9:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiRecord {
    private boolean chi;
    private List<FuluRecord> records;

    public ChiRecord(boolean chi, FuluRecord... record) {
        this.records = Arrays.asList(record);
    }

    public ChiRecord(boolean chi) {
        this.chi = chi;
    }
}
