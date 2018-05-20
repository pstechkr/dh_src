package client.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientController {

	// 사장님 로그인 인덱스 페이지
	@RequestMapping("/index")
	public ModelAndView index(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/client/index");
		
		return mav;
	}
	
	// 회원가입 페이지
	@RequestMapping("/join")
	public ModelAndView join(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/client/join");
		
		return mav;
	}
	// 사장님 로그인 이후 메인 페이지 추후 바뀔 듯
	@RequestMapping("/main")
	public ModelAndView main(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/client/main");
		
		return mav;
	}
}
