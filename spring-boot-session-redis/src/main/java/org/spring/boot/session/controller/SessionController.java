package org.spring.boot.session.controller;

import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
	
	@GetMapping("/sessionId")
	public String getSessionId(HttpServletRequest request, HttpSession session) throws ServletException {
		String uid = null;
		if (request.getSession().getAttribute("uid") != null) {
			uid = request.getSession().getAttribute("uid").toString();
		}else {
			uid = UUID.randomUUID().toString();
		}
		request.getSession().setAttribute("uid", uid);
		return request.getSession().getId();
	}
	
}
