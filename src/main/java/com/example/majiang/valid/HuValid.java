package com.example.majiang.valid;

import com.example.majiang.*;

import java.util.List;

public interface HuValid extends BaseValid {
    Fan valid(HandMajDistribution hmd, List<MajGroup> show, List<Maj> discard, List<MajGroup> handMajGroups, GameInfo gameInfo);
}
