package com.acme.xxlightingcontrol.lib.xutil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author jx9@msn.com
 */
public class LunarCalendar {

    private static final String[] GAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] ZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] ANIMAL = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};


    public static String[] getGanZhi(int year, int month, int day) {
        int ganIndex = (year - 1900 + 36) % 10; // 甲子年是1904年，所以起始年份要稍微处理一下
        int zhiIndex = (year - 1900 + 36) % 12;
        String ganYear = GAN[ganIndex];
        String zhiYear = ZHI[zhiIndex];
        String animalYear = ANIMAL[zhiIndex];

        int monthGanIndex = (year - 1900) * 12 + (month - 1) ;
        int monthZhiIndex = (month - 1);
        String ganMonth = GAN[monthGanIndex % 10];
        String zhiMonth = ZHI[monthZhiIndex % 12];

        int dayGanIndex = (int) ((year - 1900) * 365.25 + (month - 1) * 30.44 + day - 1 + 36) % 10;
        int dayZhiIndex = (int) ((year - 1900) * 365.25 + (month - 1) * 30.44 + day - 1 + 36) % 12;
        String ganDay = GAN[dayGanIndex % 10];
        String zhiDay = ZHI[dayZhiIndex % 12];


        return new String[]{ganYear + zhiYear + "年", ganMonth + zhiMonth + "月", ganDay + zhiDay + "日", animalYear};
    }


    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2025, Calendar.AUGUST, 7);
        Date date = calendar.getTime();
        String[] ganZhi = getGanZhi(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("公历日期: " + calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
        System.out.println("农历干支: " + ganZhi[0] + "  " + ganZhi[1] + " " + ganZhi[2] + "  " + ganZhi[3] + "年");
    }
}