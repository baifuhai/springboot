package com.test.amqp;

import com.test.amqp.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot12AmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void createExchangeAndQueue() {
		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));

		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

		amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));

		amqpAdmin.deleteExchange("amqpadmin.exchange");

		amqpAdmin.deleteQueue("amqpadmin.queue");
	}

	@Test
	public void testConvertAndSend() {
		//Message需要自己构造一个，定义消息体内容和消息头
		//rabbitTemplate.send(exchange, routingKey, message);

		//object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
		String exchange = "exchange.direct";
		String routingKey = "queue.emps";
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "hello");
		map.put("data", Arrays.asList(1, "2", true));
		rabbitTemplate.convertAndSend(exchange, routingKey, map);
	}

	@Test
	public void testReceiveAndConvert() {
		//接收数据，默认是将数据序列化，要将数据转为json发送出去，要往容器里放一个MessageConverter
		Object object = rabbitTemplate.receiveAndConvert("queue.emps");
		System.out.println(object.getClass());
		System.out.println(object);
	}

	@Test
	public void testConvertAndSend2() {
		String exchange = "exchange.fanout";
		String routingKey = "";
		Object object = new Book("bookName", "author");
		rabbitTemplate.convertAndSend(exchange, routingKey, object);
	}

	@Test
	public void testReceiveAndConvert2() {
		Object object = rabbitTemplate.receiveAndConvert("queue.emps");
		System.out.println(object.getClass());
		System.out.println(object);
	}

}
