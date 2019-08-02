package com.yq.android_recruit.util;


import org.springframework.core.convert.converter.Converter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateEdtor implements Converter<String,Date>,Serializable {

    public Date detailConvert(String s) {
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return DateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convert(Date s){
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            return DateFormat.format(s);
    }

    @Override
    public Date convert(String s) {
        return null;
    }
}
