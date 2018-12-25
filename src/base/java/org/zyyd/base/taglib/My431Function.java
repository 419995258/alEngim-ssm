package org.zyyd.base.taglib;

import org.zyyd.base.entity.BaseProperties;
import org.zyyd.base.util.DateFormater;
import org.zyyd.base.util.MD5;
import org.zyyd.base.util.SpringContextUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import redis.RedisUtil;

public class My431Function {

	public static String UTC(String date) {
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		df2.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date ndt;
		try {
			ndt = df2.parse(date);
			return DateFormater.DateToString(ndt, "yyyy-MM-dd HH:mm:ss:S");
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

	}

}
