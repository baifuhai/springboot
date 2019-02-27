package com.test.springboot;

import com.test.springboot.entity.User;
import com.test.springboot.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot06DataJpaApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void testFind() {
		User user = userRepository.findOne(1);
		System.out.println(user);
	}

	@Test
	public void insertUser() {
		User user = new User();
		user.setLastName("a");
		user.setEmail("a@126.com");
		userRepository.save(user);
		System.out.println(user);
	}

}
