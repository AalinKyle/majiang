package com.example.majiang.strategy;

import com.example.majiang.GameInfo;
import com.example.majiang.HuRecord;
import com.example.majiang.valid.ChiRecord;
import com.example.majiang.valid.FuluObj;
import com.example.majiang.valid.GangRecord;
import com.example.majiang.valid.PengRecord;

import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/23 11:28
 */
public interface MajStrategy<T> {
    /**
     * 打出一张牌
     *
     * @param gameInfo
     * @return
     */
    public T play(GameInfo gameInfo);

    /**
     * 选择杠不杠
     * * 不杠返回null
     * * 进入这个方法前提是可以杠
     * 因为有可能同时存在明杠暗杠，所以传进来是list
     *
     * @param records
     * @param gameInfo
     * @return
     */
    public FuluObj chooseGang(List<GangRecord> records, GameInfo gameInfo);

    /**
     * 选择碰不碰
     * * 不碰返回null
     * * 进入这个方法前提是可以碰
     *
     * @param record
     * @param gameInfo
     * @return
     */
    public FuluObj choosePeng(PengRecord record, GameInfo gameInfo);

    /**
     * 选择吃不吃
     * 不吃返回null
     * 进入这个方法前提是可以吃
     *
     * @param record
     * @param gameInfo
     * @return
     */
    public FuluObj chooseChi(ChiRecord record, GameInfo gameInfo);

    boolean chooseHu(HuRecord huRecord, GameInfo touchGameInfo);

}
