package com.berna.global.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String returnSinglePage(Model model) {
		model.addAttribute("projectName", "Reservation System");

		return "reservation";
	}


}
