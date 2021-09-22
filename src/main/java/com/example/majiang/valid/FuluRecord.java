package com.example.majiang.valid;

import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 17:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuluRecord {
    protected MajGroup group;
    protected List<Maj> needRemoveHand;
}
