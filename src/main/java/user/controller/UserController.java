package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
	
	// 회원가입 페이지
	@RequestMapping("/join")
	public ModelAndView join(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/join/join");
	
		return mav;
	}

	// 로그인 페이지
	@RequestMapping("/login")
	public ModelAndView login(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/join/login");
	
		return mav;
	}

	// 로그아웃
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpSession httpsession){

		// 제거.
		httpsession.invalidate();
		
		String redirectURL = request.getSession().getServletContext().getContextPath();
//		if(request.getParameter("refer") != ""){redirectURL = request.getParameter("refer");}
		return new ModelAndView(new RedirectView(redirectURL));
	}
	
	// 비밀번호 찾기 페이지
	@RequestMapping("/find_password")
	public ModelAndView find_password(){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/join/findpassword");
	
		return mav;
	}
	
	// 크롬으로 페이스북 로그인 할 때 리다이렉트
	@RequestMapping("/fbchrome")
	public ModelAndView facebookR(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/join/fbchrome");
		return mav;
	}
}
