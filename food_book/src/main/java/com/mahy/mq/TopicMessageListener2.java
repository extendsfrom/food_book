package com.mahy.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicMessageListener2 {
	private static final Logger logger = LoggerFactory.getLogger(TopicMessageListener2.class);
	
	public void receiveMessage(PhoneNoticeInfo noticeInfo) {
		logger.info("topic2收到消息：" + noticeInfo.toString());
		System.out.println("topic2收到消息：" + noticeInfo.toString());
	}
}
