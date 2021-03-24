package org.spring.boot.controller;

import org.spring.boot.entity.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class MainController {

	@GetMapping("/homePage")
	public String homePage(HttpServletRequest request) {
		System.out.println("------11111");
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

	@PostMapping(value = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadImage(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "flag", defaultValue = "PV") String flag) {
		System.out.println("-------");
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
