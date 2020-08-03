package com.springboot.firstspringbootproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.firstspringbootproject.configuration.BasicConfiguration;
import com.springboot.firstspringbootproject.service.WelcomeService;

@RestController
public class WelcomeController {
	
	
	@Autowired
	private WelcomeService service;
	
	@Autowired
	private BasicConfiguration basic;

	@RequestMapping("/welcome")
	public String welcome() {
		return  service.retrieveWelcomeMessage();
	}
	
	@RequestMapping("/dynamic-configuration")
	public Map dynamicConfigiration() {
		Map map = new HashMap();
		map.put("message",basic.getMessage());
		map.put("number", basic.getNumber());
		map.put("value", basic.isValue());
		
		return map;
		
	}
}

