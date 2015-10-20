package com.scu.book.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/blog")
public class BlogCol {
	
	@RequestMapping(value = "index")
	public String index() {
		return "blog/index";
	}
}
