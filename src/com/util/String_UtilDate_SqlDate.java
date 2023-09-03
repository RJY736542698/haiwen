package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class String_UtilDate_SqlDate {
    //String类型转换成java.util.date
    public static Date stringToUtuilDate(String datevalue) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(datevalue);
        return  date;
    }
    //java.util.date转换成 java.sql.date
    public static java.sql.Date utilDateToSqlDate(Date datevalue){
        return new java.sql.Date(datevalue.getTime());
    }
}
