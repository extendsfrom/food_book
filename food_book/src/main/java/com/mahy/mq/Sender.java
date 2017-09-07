package com.mahy.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("sender")
public class Sender {
	@Autowired
	private QueueMessageProducer queueMessageProducer;
	
	@Autowired
	private TopicMessageProducer topicMessageProducer;
	/**
	 * 发送点对点信息
	 * 
	 * @param noticeInfo
	 */
	@RequestMapping("/setQueueSender")
	public void setQueueSender() {
		PhoneNoticeInfo noticeInfo = new PhoneNoticeInfo();
		noticeInfo.setNoticeContent("Hello Word");
		noticeInfo.setNoticeTitle("hello Word");
		noticeInfo.setReceiver("hello");
		noticeInfo.setReceiverPhone("1111111");
		queueMessageProducer.sendQueue(noticeInfo);
		return;
	}

	/**
	 * 发送订阅信息
	 * 
	 * @param noticeInfo
	 */
	@RequestMapping("/setTopicSender")
	public void setTopicSender() {
		PhoneNoticeInfo noticeInfo = new PhoneNoticeInfo();
		noticeInfo.setNoticeContent("Hello Word");
		noticeInfo.setNoticeTitle("hello Word");
		noticeInfo.setReceiver("hello");
		noticeInfo.setReceiverPhone("1111111");
		topicMessageProducer.sendTopic(noticeInfo);
		return;
	}

}
