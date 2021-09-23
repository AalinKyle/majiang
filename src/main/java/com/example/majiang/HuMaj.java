package com.example.majiang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HuMaj {
    private List<Maj> hand;
    private List<MajGroup> show;
    private List<Maj> discard;
    private Maj currentMaj;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("手牌:").append(hand).append(",");
        sb.append("副露:").append(show);
        sb.append("胡牌:").append(currentMaj);
        return sb.toString();
    }
}
