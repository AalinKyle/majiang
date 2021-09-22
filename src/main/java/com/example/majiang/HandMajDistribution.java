package com.example.majiang;

import lombok.Data;

import java.util.List;

@Data
public class HandMajDistribution {

    private int[] wan = new int[9];
    private int[] tong = new int[9];
    private int[] suo = new int[9];
    private int[] zi = new int[9];

    public HandMajDistribution(List<Maj> hand) {

        if (hand == null) {
            throw new NullPointerException();
        }

        for (Maj t : hand) {
            int type = t.getType();
            int content = t.getContent();
            switch (type) {
                case 0: {
                    wan[content]++;
                    break;
                }
                case 1: {
                    tong[content]++;
                    break;
                }
                case 2: {
                    suo[content]++;
                    break;
                }
                case 3: {
                    zi[content]++;
                    break;
                }
            }
        }
    }

}
