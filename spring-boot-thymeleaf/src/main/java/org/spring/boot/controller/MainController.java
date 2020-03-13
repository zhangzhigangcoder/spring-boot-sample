package org.spring.boot.controller;

import org.spring.boot.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@ResponseBody
	@GetMapping("/home")
	public String homePage() {
		return "hello world";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("name","zhangzhigang12655");
		return "index";
	}

	@GetMapping("/hello")
	public String hello() throws Exception {
		throw new Exception("发生错误");
	}

	@GetMapping("/json")
	public String json() throws MyException {
		throw new MyException("发生错误2");
	}

}
