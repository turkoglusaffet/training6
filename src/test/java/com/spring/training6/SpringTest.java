package com.spring.training6;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.training6.config.ApplicationConfig;
import com.spring.training6.entity.TblUser;
import com.spring.training6.jpa.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SpringTest {

	@Autowired
	UserRepository repository;

	
	
	@Test
	@Transactional
	public void test2() {
		TblUser user = new TblUser();
		user.setFirstName("SpringORM-1");
		user.setLastName("SpringJPA-1");
		user.setEmail("jpa@spring.com");
		
		Thread thread = new Thread(()->{
			repository.insert(user);
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();
		
		
		TblUser user2 = new TblUser();
		user2.setId(22);
		user2.setFirstName("SpringORM-2");
		user2.setLastName("SpringJPA-2");
		user2.setEmail("jpa@spring.com");
		repository.update(user2);
		
		
		List<TblUser> users = repository.findAll();
		for (TblUser u : users) {
			System.out.println(u);
		}
	}
	@Test
		public void test1() {
			List<TblUser> users = repository.findAll();
			for (TblUser user : users) {
				System.out.println(user);
			}
		}
	
	
}
