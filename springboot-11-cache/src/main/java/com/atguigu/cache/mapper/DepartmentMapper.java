package com.atguigu.cache.mapper;

import com.atguigu.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDeptById(Integer id);

}
