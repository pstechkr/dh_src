package community.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/community")
public class UserCommunityController {

	// Community Review Index (현재에는 index로 활용)
	@RequestMapping("/index")
	public ModelAndView review_index(HttpServletRequest request){
		
		String redirectURL = request.getSession().getServletContext().getContextPath();
		redirectURL += "/community/review/list";
		return new ModelAndView(new RedirectView(redirectURL));
	}
	@RequestMapping("/review/list/")
	public ModelAndView review_redirect(HttpServletRequest request){
		
		String redirectURL = request.getSession().getServletContext().getContextPath();
		redirectURL += "/community/review/list";
		return new ModelAndView(new RedirectView(redirectURL));
	}

}
