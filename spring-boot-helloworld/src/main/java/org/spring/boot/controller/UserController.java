package org.spring.boot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.boot.entity.User;
import org.spring.boot.properties.OrderProperties;
import org.spring.boot.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 构建一个简单用户restful api
 * 
 * @author zhangzhigang
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private OrderProperties orderProperties;

	//创建线程安全的Map
	private static Map<Long,User> users = Collections.synchronizedMap(new HashMap<>());
	
	@GetMapping("/")
	public List<User> getUserList() {
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息传递
		List<User> userList = new ArrayList<>(users.values());
		System.out.println(orderProperties.getId());
		return userList;
	}
	
	@PostMapping(path="/")
	public String postUser(@RequestBody User user) {
		//除了@ModelAttribute绑定参数外，还可以通过@RequestParam从页面中传递参数
		users.put(user.getId(), user);
		return "success";
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id,@RequestParam String username) {
		//url中的id通过@PathVariable绑定到方法参数中
		return users.get(id);
	}
	
	@PutMapping("/{id}")
	public String putUser(@PathVariable Long id,@RequestBody User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}

}
