package com.oak.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.oak.challenge.config.V1RestController;

@V1RestController
public class TestController {

	@GetMapping("test")
	public String test() {
		return "test";
	}
	
}
