package client.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ScheduleController {

	// 스케줄 관리 페이지
	@RequestMapping("/schedule")
	public ModelAndView schedule(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/client/schedule");
		
		return mav;
	}
}
