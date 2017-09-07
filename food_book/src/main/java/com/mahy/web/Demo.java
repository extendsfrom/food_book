package com.mahy.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
	public static void main(String[] args) {
		String str = "20170111141215";
		long waite = Long.parseLong("1440");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMddHHmmss").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date.getTime() + (waite * 60 * 1000))) );
	}
}
