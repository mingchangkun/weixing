
package com.bj.controller;

import com.bj.utility.CheckUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HelloConttoller extends BaseController{

	@RequestMapping("/hello2")
	public String hello2(ModelMap map) {
		map.addAttribute("name","小明");

		return "index";
	}

	 public static void main(String[] args) {
	 SpringApplication.run(HelloConttoller.class, args);
	}

	@RequestMapping("/test2")
	public String test2() {
		int i = 1 / 0;
		return "test2";
	}

}
