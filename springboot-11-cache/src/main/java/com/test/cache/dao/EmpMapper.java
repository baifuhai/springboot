package com.test.cache.dao;

import com.test.cache.model.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EmpMapper {

	@Select("select * from emp where lastName = #{lastName}")
	Emp getEmpByLastName(String lastName);

	@Select("select * from emp where id = #{id}")
	Emp getById(Integer id);

	@Update("update emp set last_name = #{lastName}, email = #{email}, gender = #{gender}, dept_id = #{deptId} where id = #{id}")
	int update(Emp emp);

	@Delete("delete from emp where id = #{id}")
	int delete(Integer id);

	@Insert("insert into emp (last_name, email, gender, dept_id) values (#{lastName}, #{email}, #{gender}, #{deptId})")
	int insert(Emp emp);

}