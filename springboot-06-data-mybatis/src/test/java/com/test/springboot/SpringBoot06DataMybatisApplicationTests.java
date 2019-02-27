package com.test.springboot;

import com.test.springboot.bean.Department;
import com.test.springboot.bean.Employee;
import com.test.springboot.mapper.DepartmentMapper;
import com.test.springboot.mapper.EmployeeMapper;
import javafx.scene.DepthTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot06DataMybatisApplicationTests {

	@Autowired
	DepartmentMapper departmentMapper;

	@Autowired
	EmployeeMapper employeeMapper;

	@Test
	public void testGetDept() {
		Department dept = departmentMapper.getById(1);
		System.out.println(dept);
	}

	@Test
	public void testInsertDept() {
		Department dept = new Department();
		dept.setDepartmentName("dev");
		departmentMapper.insert(dept);
		System.out.println(dept);
	}

	@Test
	public void testGetEmp() {
		Employee emp = employeeMapper.getById(1);
		System.out.println(emp);
	}

}
