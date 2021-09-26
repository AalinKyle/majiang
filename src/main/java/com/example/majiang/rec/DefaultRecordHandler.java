package com.example.majiang.rec;

import com.example.majiang.*;
import com.example.majiang.p.BasePlayer;
import com.example.majiang.utils.FanUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        rec(settleAccount);
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

    /**
     * 实际上 index =>0 是不会触发的
     */
    private static final String[] HU_DESC = new String[]{"没胡", "放炮胡", "一炮双响", "一炮三响"};

    private void rec(GameSettleAccount account) {
        List<WinnerRecord> winnerRecords = account.getWinnerRecords();
        int changFeng = account.getChangFeng();
        int roundNum = account.getRoundNum();
        Map<Integer, BasePlayer> noMap = account.getNoMap();
        BasePlayer dealer = noMap.get(account.getDealer());
        List<Maj> baoPais = account.getBaoPais();
        int type = account.getWinType();
        if (winnerRecords.size() == 2) {
            log.info("xxxxxxxxx");
        }
        for (WinnerRecord wrec : winnerRecords) {
            record(wrec);
            HuRecord huRecord = wrec.getRecord();
            String winnerName = wrec.getWinnerName();
            List<String> loserNames = wrec.getLoserNames();
            switch (type) {
                case GameSettleAccount.Type.ZI_MO: {
                    log.info("第{}局,场风{}庄家{},宝牌{},{}手,#{}# 自摸胡=>{},番数:{}",
                            MajGame.gameNum.get(),
                            Maj.zi[changFeng],
                            dealer.getName(),
                            baoPais,
                            roundNum,
                            winnerName,
                            huRecord.getHuMaj(),
                            buildFanString(huRecord.getFans()));
                    break;
                }
                case GameSettleAccount.Type.FANG_PAO: {
                    log.info("第{}局,场风{}庄家{},宝牌{},{}手,#{}# 胡 #{}# {}=>{},番数:{}",
                            MajGame.gameNum.get(),
                            Maj.zi[changFeng],
                            dealer.getName(),
                            baoPais,
                            roundNum,
                            winnerName,
                            loserNames,
                            HU_DESC[winnerRecords.size()],
                            huRecord.getHuMaj(),
                            buildFanString(huRecord.getFans()));

                    break;
                }
                default: {

                }
            }
        }

    }


    private void record(WinnerRecord record) {
        HuRecord huRecord = record.getRecord();
        String winnerName = record.getWinnerName();
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


    private String buildFanString(List<Fan> fans) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = buildMap(fans);
        if (fans.size() > 0) {
            if (fans.get(0).isYiMan()) {
                sb.append(sumFan(fans)).append("倍役满");
            } else {
                sb.append("共").append(sumFan(fans)).append("番");
            }
            sb.append(",");
            for (Fan fan : fans) {
                int prefixNum = 1;
                String type = fan.getType();
                Integer num = map.get(type);
                if (num > 1) {
                    prefixNum *= num;
                }
                if (fan.isSingle()) {
                    prefixNum *= (fan.getNum());
                }
                if (prefixNum > 1) {
                    sb.append(prefixNum);
                }
                sb.append(fan.getType());
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> buildMap(List<Fan> list) {
        Map<String, Integer> map = new HashMap<>(list.size());
        for (Fan f : list) {
            String type = f.getType();
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        return map;
    }

}
