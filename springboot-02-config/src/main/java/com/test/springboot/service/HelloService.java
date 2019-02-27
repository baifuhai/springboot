package com.test.springboot.service;

import com.test.springboot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Value("${person.last-name}")
    String name;

    @Autowired
    Person person;

    public String getName() {
        return name;
    }

    public Person getPerson() {
        return person;
    }

}
