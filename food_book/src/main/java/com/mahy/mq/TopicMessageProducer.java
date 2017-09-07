package com.mahy.mq;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class TopicMessageProducer {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination notifyTopic;

	public void sendTopic(PhoneNoticeInfo noticeInfo) {
		jmsTemplate.convertAndSend(notifyTopic, noticeInfo);
	}

}
