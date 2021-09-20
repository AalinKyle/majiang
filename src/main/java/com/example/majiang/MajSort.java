package com.example.majiang;

import java.util.Comparator;

public class MajSort implements Comparator<Maj> {
    @Override
    public int compare(Maj o1, Maj o2) {
        int ot1 = o1.getType();
        int ot2 = o2.getType();
        if (ot1 != ot2) {
            return ot1 - ot2;
        } else {
            int oc1 = o1.getContent();
            int oc2 = o2.getContent();
            return oc1 - oc2;
        }
    }
}