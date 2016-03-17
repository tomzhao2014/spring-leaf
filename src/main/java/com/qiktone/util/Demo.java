package com.qiktone.util;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 14:43
 */
public class Demo {
    public static void main(String[] args){
        System.out.println(DateUtils.getCurrentYear());
        Date date1 = DateUtils.getNextDate(new Date(),-1,1);
        System.out.println(DateUtils.getDateString(date1));
    }
}
