package com.test.cache.service;

import com.test.cache.dao.DeptMapper;
import com.test.cache.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = {"dept"}, cacheManager = "deptCacheManager")
@Service
public class DeptService {

    @Autowired
    DeptMapper deptMapper;

    @Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;

    /**
     *  缓存的数据能存入redis；
     *  第二次从缓存中查询就不能反序列化回来；
     *  存的是dept的json数据;CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     *
     * @param id
     * @return
     */
    @Cacheable(/*cacheNames = "dept", cacheManager = "deptCacheManager"*/)
    public Dept getById(Integer id){
        Dept dept = deptMapper.getDeptById(id);
        return dept;
    }

    // 使用缓存管理器得到缓存，进行api调用
    public Dept getById2(Integer id){
        Dept dept = deptMapper.getDeptById(id);

        //获取某个缓存
        Cache cache = deptCacheManager.getCache("dept");
        cache.put(id, dept);

        return dept;
    }

}
