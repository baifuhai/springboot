package com.test.cache.controller;

import com.test.cache.model.Emp;
import com.test.cache.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @RequestMapping("getById")
    public Emp getById(Integer id) {
        return empService.getById(id);
    }

    @RequestMapping("update")
    public String update(Emp emp) {
        empService.update(emp);
        return "success";
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        empService.delete(id);
        return "success";
    }

    @RequestMapping("insert")
    public String insert(Emp emp) {
        empService.insert(emp);
        return "success";
    }

}
