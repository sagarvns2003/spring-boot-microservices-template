package com.vidya.cal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Vidya Sagar Gupta
 *
 */
@Controller
public class HomeController {

	@GetMapping(path = {"/", "/home", "index"})
    public String homePage(Model model) {
        //model.addAttribute("appName", appName);
        return "index";
    }
	
}