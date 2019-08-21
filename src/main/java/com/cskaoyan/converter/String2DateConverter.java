package com.cskaoyan.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: XiaoLei
 * @Date Created in 20:42 2019/8/20
 */
@Component
public class String2DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {

        if (s != null && !"".equals(s)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parse = null;
            try {
                parse = simpleDateFormat.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
            return parse;
        }
        else return null;
    }

}
