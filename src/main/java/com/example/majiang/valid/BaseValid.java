package com.example.majiang.valid;

import com.example.majiang.MajGroup;
import com.example.majiang.ShowEswnzfbx;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/22 15:12
 */
public interface BaseValid {
    default boolean v(int[] arr, int num) {
        return sum(arr) == num;
    }

    default int sum(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return sum;
    }

    default boolean allSame(int[] arr, int target) {
        for (int i : arr) {
            if (i != target) {
                return false;
            }
        }
        return true;
    }

    default ShowEswnzfbx parseShow(List<MajGroup> groups) {
        return ShowEswnzfbx.build(groups);
    }

 }
