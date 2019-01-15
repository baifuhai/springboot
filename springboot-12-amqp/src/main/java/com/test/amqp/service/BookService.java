package com.test.amqp.service;

import com.test.amqp.model.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "queue.emps")
    public void receive01(Book book){
        System.out.println("queue.emps receive: " + book);
    }

    @RabbitListener(queues = "queue.news")
    public void receive02(Message message){
        System.out.println("queue.news receive: ");
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }

    @RabbitListener(queues = "queue2.news")
    public void receive03(Message message){
        System.out.println("queue2.news receive: ");
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }

}
