package com.example.majiang.valid;

import com.example.majiang.MajGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 15:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreValidRes {
    private boolean valid;
    private List<MajGroup> handMajGroups;
}
