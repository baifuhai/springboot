package com.test.cache;

import com.test.cache.model.Emp;
import com.test.cache.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot11CacheApplicationTests {

	@Autowired
	EmpService empService;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate<Object, Emp> empRedisTemplate;

	@Test
	public void test() {
		Emp emp = empService.getById(1);
		System.out.println(emp);
	}

	/**
	 * Redis常见的五大数据类型
	 *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
	 *  stringRedisTemplate.opsForValue()[String（字符串）]
	 *  stringRedisTemplate.opsForList()[List（列表）]
	 *  stringRedisTemplate.opsForSet()[Set（集合）]
	 *  stringRedisTemplate.opsForHash()[Hash（散列）]
	 *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
	 */
	@Test
	public void test01(){
		stringRedisTemplate.opsForValue().append("msg", "hello");
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);

		stringRedisTemplate.opsForList().leftPush("list", "a");
		stringRedisTemplate.opsForList().leftPush("list", "b");
		Long size = stringRedisTemplate.opsForList().size("list");
		for (int i = 0; i < size; i++) {
			String s = stringRedisTemplate.opsForList().index("list", i);
			System.out.println(s);
		}

		stringRedisTemplate.opsForHash().put("sms_code", "13306531111", "111111");
		stringRedisTemplate.opsForHash().put("sms_code", "13306532222", "222222");
		System.out.println(stringRedisTemplate.opsForHash().get("sms_code", "13306531111"));
		System.out.println(stringRedisTemplate.opsForHash().get("sms_code", "13306532222"));

		//redisTemplate.delete("msg");
		//redisTemplate.delete("list");
		//redisTemplate.delete("sms_code");
	}

	//测试保存对象
	@Test
	public void test02(){
		Emp emp = empService.getById(1);

		//如果保存对象，默认使用jdk序列化机制，序列化后的数据保存到redis中
		redisTemplate.opsForValue().set("emp-a", emp);

		//1、将数据以json的方式保存
		//(1)自己将对象转为json
		//(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
		empRedisTemplate.opsForValue().set("emp-b", emp);
	}

}
