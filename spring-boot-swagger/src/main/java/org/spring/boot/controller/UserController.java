package org.spring.boot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.boot.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 构建一个简单用户restful api
 * 
 * @author zhangzhigang
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	//创建线程安全的Map
	private static Map<Long,User> users = Collections.synchronizedMap(new HashMap<>());
	
	@ApiOperation(value="获取用户列表", notes="", httpMethod="GET", produces= MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/")
	public List<User> getUserList() {
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息传递
		List<User> userList = new ArrayList<>(users.values());
		return userList;
	}
	
	@ApiOperation(value="创建用户", notes="根据User对象创建用户", httpMethod="POST")
	@ApiImplicitParam(name="user", value="用户详细实体user", required = true, dataType = "User")
	@PostMapping(path="/")
	public String postUser(@RequestBody User user) {
		//除了@ModelAttribute绑定参数外，还可以通过@RequestParam从页面中传递参数
		users.put(user.getId(), user);
		return "success";
	}
	
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息", httpMethod="GET")
	@ApiImplicitParam(name="id", value="用户ID", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id,@RequestParam String username) {
		//url中的id通过@PathVariable绑定到方法参数中
		return users.get(id);
	}
	
	@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息", httpMethod="PUT")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="用户ID", required=true, dataType="Long", paramType="path"),
		@ApiImplicitParam(name="user", value="用户详细实体user", required=true, dataType="User")
	})
	@PutMapping("/{id}")
	public String putUser(@PathVariable Long id,@RequestBody User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}
	
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象", httpMethod="DELETE")
	@ApiImplicitParam(name="id", value="用户ID", required=true, dataType="Long", paramType="path")
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}

}
