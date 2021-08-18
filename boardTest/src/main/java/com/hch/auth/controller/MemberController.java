package com.hch.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping({"/","/login"})
	public String login(){
		return "/auth/login";
	}
	
}
