package com.cy.douyin.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author 86147
 * create  20/6/2023 下午5:10
 */
public class MessageLoadUtil {
    private static final HashMap<Integer, Date> messageLoadHistory = new HashMap<>();


    public static boolean isFirst(Integer userId){
        if (messageLoadHistory.containsKey(userId) && !isDateDifferenceGreaterThanOneDay(new Date(),messageLoadHistory.get(userId))) {
            return false;
        }
        else {
            messageLoadHistory.put(userId,new Date());
        }
        return true;
    }

    private static boolean isDateDifferenceGreaterThanOneDay(Date date1, Date date2) {
        long timeDifference = Math.abs(date1.getTime() - date2.getTime()); // 计算时间差，取绝对值
        long oneDayTime = 60 * 60 * 1000; // 一天的时间毫秒数
        return timeDifference > oneDayTime;
    }

}
