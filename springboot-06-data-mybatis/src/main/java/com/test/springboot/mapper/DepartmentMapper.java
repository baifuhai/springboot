package com.test.springboot.mapper;

import com.test.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

//指定这是一个操作数据库的mapper
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    Department getById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department (department_name) values (#{departmentName})")
    int insert(Department department);

    @Update("update department set department_name = #{departmentName} where id = #{id}")
    int update(Department department);

    @Delete("delete from department where id = #{id}")
    int delete(Integer id);
}
