package com.mahy.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueMessageListener {
	private static Logger logger = LoggerFactory.getLogger(QueueMessageListener.class);
	
	public void receiveMessage(PhoneNoticeInfo noticeInfo) {
		try {
			logger.info("queue收到消息：" + noticeInfo.toString());
			System.out.println("queue收到消息:" + noticeInfo.toString());
		} catch (Exception e) {
			logger.error("处理信息时发生异常", e);
		}
	}

}
