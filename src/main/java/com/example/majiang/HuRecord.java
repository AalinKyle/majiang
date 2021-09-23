package com.example.majiang;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class HuRecord {
    private boolean yiMan;
    private boolean hu;
    private Date huTime;
    private List<Fan> fans;
    private HuMaj huMaj;
    private int fu;

    private int changFeng;
    private int ziFeng;
}
