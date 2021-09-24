package com.example.majiang.p;

import com.example.majiang.Maj;
import com.example.majiang.MajGroup;
import com.example.majiang.strategy.MajStrategy;

import java.util.Comparator;
import java.util.List;

/**
 * @param <T>
 * @param <U>
 * @author kyle
 */
public interface Player<T extends Maj, U extends MajGroup> extends MajStrategy<T>{
    /**
     * 获取id
     *
     * @return
     */
    public String getName();

    /**
     * 获取副露的牌
     *
     * @return
     */
    public List<U> getShow();

    /**
     * 获取弃牌区
     *
     * @return
     */
    public List<T> getDiscard();


    /**
     * 增加副露
     *
     * @param group
     * @param discard 副露完需要在手牌中去掉的牌
     */
    public void addShow(U group, List<T> discard);

    /**
     * 增加副露
     *
     * @param group
     * @param discard 副露完需要在手牌中去掉的牌
     */
    public void addShow(U group, List<T> discard, List<U> needRemoveShow);

    /**
     * 获取手牌
     *
     * @return
     */
    public List<T> getHand();

    /**
     * 摸牌
     *
     * @param maj
     */
    public void touch(T maj);

    /**
     * 摸牌
     *
     * @param maj
     */
    public void touch(T maj, Comparator<T> sort);

    /**
     * 丢去弃牌区
     *
     * @param t
     */
    public void addDiscard(T t);

    /**
     * 结束
     */
    public void over();


}
