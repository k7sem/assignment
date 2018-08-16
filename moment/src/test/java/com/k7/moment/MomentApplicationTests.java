package com.k7.moment;

import com.k7.moment.mapper.UrlMapper;
import com.k7.moment.mapper.UserMapper;
import com.k7.moment.pojo.Url;
import com.k7.moment.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MomentApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UrlMapper urlMapper;


	@Test
	public void contextLoads() {
		System.out.println("testing");

		//urlMapper.insertImgUrlByUserID("123456", 4);
		//urlMapper.insertImgUrlByUserID("657899", 4);


		//userMapper.insertUser("zhang san");

		//userMapper.insertUser("wang wu");

		//userMapper.insertUser("zhao liu");

		//userMapper.updateUser("zhang xiao san", 4);

		//List<User> users = userMapper.getAllUsers();
		//System.out.println("users.length before = " + users.size());

		//userMapper.deleteAllUsers();

		//List<User> users1 = userMapper.getAllUsers();
		//System.out.println("users.length after = " + users1.size());

		//User u = userMapper.findUserByPhone("12345678910");
		//Assert.assertEquals("winterchen", u.getName());

		/*List<Url> urls = urlMapper.getAllUrlByUserID(4);

		for (Url u : urls) {
			System.out.println("url: " + u.getImgurl());
		}*/

		urlMapper.deleteAll();
		userMapper.deleteAllUsers();
	}

}
