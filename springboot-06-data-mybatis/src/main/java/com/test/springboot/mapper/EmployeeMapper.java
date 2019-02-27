package com.test.springboot.mapper;

import com.test.springboot.bean.Employee;

//@Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {

    Employee getById(Integer id);

    int insert(Employee employee);

}
