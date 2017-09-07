package com.mahy.mq;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class QueueMessageProducer {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination notifyQueue;

	public void sendQueue(PhoneNoticeInfo noticeInfo) {
		jmsTemplate.convertAndSend(notifyQueue, noticeInfo);
	}

}
