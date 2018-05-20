package admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/coupon")
public class CouponController {

	// 아직 진행 안 됨 
	@RequestMapping("/list")
	public ModelAndView gathering_index(){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/coupon/coupon_list");
		return mav;
		
	}
	
}
