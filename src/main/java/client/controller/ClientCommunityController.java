package client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientCommunityController {

	// 고객관리 페이지
	@RequestMapping("/community")
	public ModelAndView index(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/client/community");
		
		return mav;
	}
	
}
