package com.test.cache.dao;

import com.test.cache.model.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeptMapper {

    @Select("select * from dept where id = #{id}")
    Dept getDeptById(Integer id);

}
