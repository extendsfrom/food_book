package com.mahy.vo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constant {
	public static enum CARD_OUT_PURPOSE {
		SALE("销售","SALE"),
		ACTIVITY_ONESELF("活动自用","ACTIVITY_ONESELF"),
		PUBLIC_DONATE("公关赠送","PUBLIC_DONATE"),
		RESOURCE_EXCHANGE("资源置换","RESOURCE_EXCHANGE"),
		CHANGE_CARD("换卡","CHANGE_CARD"),
		TEST("测试","TEST"),
		OTHER("其他","OTHER");
		private String name;//名字
		private String code;//代号
		CARD_OUT_PURPOSE(String name,String code){
			this.name=name;
			this.code=code;
		}
		public String getName(){
			return this.name;
		}
		public String getCode(){
			return this.code;
		}
		public static String getCode(String name){
			for(CARD_OUT_PURPOSE item:CARD_OUT_PURPOSE.values()){
				if(item.getName().equals(name))
				{
					return item.getCode();
				}
			}
			return "";
		}
		
		public static Map<String, String> getAllMap(){
			Map<String, String> map = new LinkedHashMap<String, String>();
			for(CARD_OUT_PURPOSE item:CARD_OUT_PURPOSE.values()){
				map.put(item.getCode(), item.getName());
			}
			return map;
		}
	}
}
