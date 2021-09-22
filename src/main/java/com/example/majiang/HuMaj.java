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
}
