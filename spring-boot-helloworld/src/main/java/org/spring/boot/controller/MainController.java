package org.spring.boot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.spring.boot.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/homePage")
	public String homePage(HttpServletRequest request) {
		System.out.println(new Person().getName());
		return "hello world";
	}

	/**
	 * @RequestBody只能接收Content-Type为application/json的请求
	 * 不加@RequestBody可以接收Content-Type为application/x-www-form-urlencoded的请求
	 * 如果想接收其它Content-type类型(如text/plain)要从request中的inputstream中读取
	 * @param request
	 * @param data
	 * @return
	 */
	@PostMapping("/homePage2")
	public String callback(HttpServletRequest request, Data data) {
		System.out.println(request.getHeader("Content-Type"));
		String name = request.getParameter("name");
		System.out.println(name);
//		System.out.println(readBody(request));
		return "ok";
	}

	private String readBody(HttpServletRequest request) {
		String header = request.getHeader("Content-type");
		System.out.println(header);
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {

		}
		return stringBuilder.toString();
	}

}

class Data {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
