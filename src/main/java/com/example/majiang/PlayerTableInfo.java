package com.example.majiang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * player在牌桌上的局面 弃牌+副露
 *
 * @Author kyle
 * @create 2021/9/22 14:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerTableInfo {
    private List<MajGroup> show;
    private List<Maj> discard;

}
