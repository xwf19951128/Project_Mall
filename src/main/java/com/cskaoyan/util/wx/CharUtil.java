package com.cskaoyan.util.wx;

import java.util.Random;

public class CharUtil {
    /**
     * 自动生成32位随机英文+数字，用于给token赋值
     * */
    public static String getRandomString(Integer num) {
        //指定要使用哪些字母进行拼接
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        //新建一个字符春
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            //生成一个随机数，作为base字符串的索引。根据这个索引找到字符拼接到StringBuffer中
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
