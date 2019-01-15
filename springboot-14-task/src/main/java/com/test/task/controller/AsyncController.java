package com.test.task.controller;

import com.test.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @RequestMapping("hello")
    public String hello() {
        asyncService.hello();
        return "success";
    }

}
