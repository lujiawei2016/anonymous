package com.anonymous.start;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.anonymous.test.pojo.User;
import com.anonymous.utils.redis.RedisUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @description     redis测试
 * @author          lujiawei
 * @data            2016年12月4日 下午2:48:57
 * @version         v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class RedisTest {
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Test
	public void test(){
		
//		redisUtils.put("name", "lujiawei");
//		redisUtils.exec();
//		System.out.println(redisUtils.get("name"));
		
//		User user = new User();
//		user.setUserId(1);
//		user.setUserName("lujiawei");
//		user.setUserAge(18);
//		redisUtils.put("user", user);
//		redisUtils.exec();
//		User redisUser = redisUtils.get("user", User.class);
//		System.out.println(redisUser);
		
//		User user1 = new User(1, "lujiawei1", 18, null);
//		User user2 = new User(1, "lujiawei2", 19, null);
//		User user3 = new User(1, "lujiawei3", 20, null);
//		User user4 = new User(1, "lujiawei4", 21, null);
//		List<User> userList = new ArrayList<>();
//		userList.add(user1);
//		userList.add(user2);
//		userList.add(user3);
//		userList.add(user4);
//		redisUtils.put("userList", userList);
//		redisUtils.exec();
//		List<User> userListRedis = new Gson().fromJson(redisUtils.get("userList"), new TypeToken<List<User>>() {}.getType());
//		for(User u:userListRedis){
//			System.out.println(u.getUserName() + "-->" + u.getUserAge());
//		}
		
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("name", "小乖乖");
		userMap.put("age", 25);
		userMap.put("address", "广东省广州市海珠区");
		redisUtils.put("userMap", userMap);
		redisUtils.exec();
		
		Map<String, Object> map = new Gson().fromJson(redisUtils.get("userMap"), new TypeToken<Map<String, Object>>() {}.getType());
		System.out.println(map);
	}
	
}
