package com.mahy.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicMessageListener {
	private static final Logger logger = LoggerFactory.getLogger(TopicMessageListener.class);
	
	public void receiveMessage(PhoneNoticeInfo noticeInfo) {
		logger.info("topic收到消息：" + noticeInfo.toString());
		System.out.println("topic收到消息：" + noticeInfo.toString());
	}
}
