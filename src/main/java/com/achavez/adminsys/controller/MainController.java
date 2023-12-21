package com.achavez.adminsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/infringements")
	public String getInfringements(Model model) {
		return "main";
	}
}
