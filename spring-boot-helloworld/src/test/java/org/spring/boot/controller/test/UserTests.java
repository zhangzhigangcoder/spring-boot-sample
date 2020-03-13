package org.spring.boot.controller.test;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.spring.boot.controller.UserController;
import org.spring.boot.entity.User;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserTests {
	
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void usersTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		// 1. get 查询user列表，应该为空
		RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:9213/users/");
		mvc.perform(request)
			.andExpect(status().isOk())
			.andExpect(content().string(equalTo("[]")));
		
		// 2. post 提交一个user
		String requestJson = mapper.writeValueAsString(new User(1L,"张三",20));
		request = MockMvcRequestBuilders.post("/users/")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(requestJson);
		mvc.perform(request)
				.andExpect(content().string(equalTo("success")));
		
		// 3、get获取user列表，应该有刚才插入的数据 
        request = MockMvcRequestBuilders.get("/users/"); 
        mvc.perform(request) 
                .andExpect(status().isOk()) 
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"张三\",\"age\":20}]"))); 

        // 4、put修改id为1的user 
        requestJson = mapper.writeValueAsString(new User(null,"测试终极大师",30));
        request = MockMvcRequestBuilders.put("/users/1") 
        		.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(requestJson);
        mvc.perform(request) 
                .andExpect(content().string(equalTo("success"))); 

        // 5、get一个id为1的user 
        request = MockMvcRequestBuilders.get("/users/1").param("username", "zhang"); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}"))); 

        // 6、del删除id为1的user 
        request = MockMvcRequestBuilders.delete("/users/1"); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo("success"))); 

        // 7、get查一下user列表，应该为空 
        request = MockMvcRequestBuilders.get("/users/"); 
        mvc.perform(request) 
                .andExpect(status().isOk()) 
                .andExpect(content().string(equalTo("[]"))); 

	}
	
}
