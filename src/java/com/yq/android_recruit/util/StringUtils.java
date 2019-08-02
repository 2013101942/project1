package com.yq.android_recruit.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * String 处理工具类
 *
 */
public class StringUtils implements Serializable
{
	/**
	 * 字符串转为日期格式  ，将“yyyy-MM-dd hh-mm-ss 转化为 Util的Date”
	 * @param strDate  传进去的字符串  strDate
	 * @return         Date类型的日期
	 */
	public static Date fmtStrToDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 字符串转为Timestamp类型 的日期
	 * @param strDate  传入的字符串参数
	 * @return  返回Timestamp 格式的日期
	 */
	public static Timestamp fmtStrToTimestamp(String strDate) {
		 	return new Timestamp(fmtStrToDate(strDate).getTime());
	}
	/**
	 * Util 包中Date类型的日期转化为字符串
	 * @param date  传入的Date类型日期
	 * @return  转化后的字符串
	 */
	public static String fmtDateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	/**
	 * 将Timestamp 类型转化成字符串
	 * @param timestamp
	 * @return
	 */
	public static String TimestampToStr(Timestamp timestamp){
		Date date = new Date(timestamp.getTime());
		return  fmtDateToStr(date);
	}

	/**
	 * 将Timestamp 类型 转化成 Date类型
	 * @param timestamp
	 * @return
	 */
	public static Date TimestampToDate(Timestamp timestamp){
		return new Date(timestamp.getTime());
	}

	/**
	 * 判断字符串是否为空
	 * @param str  传入的字符串
	 * @return     Boolean类型的结果：是或否
	 */
	public static boolean isEmpty(String str) {
		if(str == null || str.equals("") || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否不为空
	 * @param str   传入的字符串
	 * @return      Boolean类型的结果：是或否
	 */
	public static  boolean isNotEmpty(String str) {
		 return !isEmpty(str);
	}
	
	/**
	 * 获得随机汉字
	 */
	public static void getRandomName() {
		StringBuilder sb = new StringBuilder();
		System.out.println(0x4E00);
		 for(int i=0 ;i<Math.ceil((Math.random()*2)+1);i++) {
		    sb.append((char)(Math.random()*(0x9FFF-2000-0x4E00)+0x4E00)); 
		 }
		 System.out.println(sb);
	}
    /**
     * 将Timestamp 类型转换为String
     * @param ts
     * @return
     */
    public static String timestampToStr(Timestamp ts){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timestampStr = simpleDateFormat.format(ts);
        return timestampStr;
    }
    
}
