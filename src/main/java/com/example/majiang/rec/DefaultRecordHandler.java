package com.example.majiang.rec;

import com.example.majiang.*;
import com.example.majiang.utils.FanUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kyle
 * @create 2021/9/26 11:38
 */
@Slf4j
public class DefaultRecordHandler implements RecordHandler {

    @Override
    public void recordMajGame(GameSettleAccount settleAccount) {
        if (settleAccount != null) {
            int result = settleAccount.getResult();
            switch (result) {
                case GameSettleAccount.Result.HU: {
                    List<WinnerRecord> winnerRecords = settleAccount.getWinnerRecords();
                    for (WinnerRecord wrec : winnerRecords) {
                        rec(wrec.getWinnerName(), wrec.getRecord());
                    }
                    break;
                }
                case GameSettleAccount.Result.LIU_JU_HUANGPAI: {
                    break;
                }
                case GameSettleAccount.Result.LIU_JU_SIFENG: {
                    log.info("第{}局,四风连打", MajGame.gameNum);
                    break;
                }
                case GameSettleAccount.Result.LIU_JU_SIGANG: {
                    log.info("第{}局,四杠散了", MajGame.gameNum);
                    break;
                }
                default: {

                }
            }
        }
    }

    private void rec(String winnerName, HuRecord huRecord) {
        for (Fan fan : huRecord.getFans()) {
            List<HuRecord> list = GlobalMajsStatistics.RECORD.getOrDefault(fan.getType(), new ArrayList<>());
            list.add(huRecord);
            GlobalMajsStatistics.RECORD.put(fan.getType(), list);
            List<HuMaj> playerRecList = GlobalMajsStatistics.PLAYER_REC.getOrDefault(winnerName, new ArrayList<>());
            playerRecList.add(huRecord.getHuMaj());
            GlobalMajsStatistics.PLAYER_REC.put(winnerName, playerRecList);

            if (GlobalMajsStatistics.MAX == null) {
                GlobalMajsStatistics.MAX = huRecord;
            } else {
                List<Fan> fans = huRecord.getFans();
                int sumFan = sumFan(fans);
                if (huRecord.isYiMan()) {
                    if (GlobalMajsStatistics.MAX.isYiMan()) {
                        if (sumFan > sumFan(GlobalMajsStatistics.MAX.getFans())) {
                            GlobalMajsStatistics.MAX = huRecord;
                        }
                    } else {
                        GlobalMajsStatistics.MAX = huRecord;
                    }
                } else {
                    if (!GlobalMajsStatistics.MAX.isYiMan()) {
                        if (sumFan > sumFan(GlobalMajsStatistics.MAX.getFans())) {
                            GlobalMajsStatistics.MAX = huRecord;
                        }
                    }
                }
            }
        }
    }

    private int sumFan(List<Fan> list) {
        return FanUtils.sumFan(list);
    }
}
