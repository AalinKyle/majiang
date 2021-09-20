package com.example.majiang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HuRecord {
    private boolean hu;
    private Date huTime;
    private List<Fan> fans;
    private HuMaj huMaj;
}
