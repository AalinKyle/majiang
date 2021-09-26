package com.example.majiang.ex;

/**
 * @Author kyle
 * @create 2021/9/26 9:33
 */
public class PoolIsEmptyException extends RuntimeException {
    public PoolIsEmptyException() {
    }

    public PoolIsEmptyException(String message) {
        super(message);
    }
}
