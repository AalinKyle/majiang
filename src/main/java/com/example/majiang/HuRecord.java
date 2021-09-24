package com.example.majiang;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class HuRecord {
    private boolean yiMan;
    private boolean hu;
    private List<Fan> fans;
    private HuMaj huMaj;

    private PointInterface dealer;
    private PointInterface winner;
    private List<PointInterface> loser;
}
