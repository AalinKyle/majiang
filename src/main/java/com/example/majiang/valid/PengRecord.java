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
public class PengRecord {
    private boolean peng;
    private List<FuluObj> records;

    public PengRecord(boolean peng, FuluObj... record) {
        this.peng = peng;
        this.records = Arrays.asList(record);
    }

    public PengRecord(boolean peng) {
        this.peng = peng;
    }
}
