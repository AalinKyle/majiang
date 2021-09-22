package com.example.majiang.valid;

import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 16:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GangRecord extends FuluRecord {
    protected boolean gang;
    protected boolean mingGang;

    public GangRecord(boolean gang) {
        this.gang = gang;
    }

    public GangRecord(boolean gang, boolean mingGang, MajGroup group, List<Maj> needRemoveHand) {
        super(group, needRemoveHand);
        this.gang = gang;
        this.mingGang = mingGang;
    }
}
