package community.controller;

import java.util.ArrayList;
import java.util.List;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import community.model.ReviewInfoModel;

@Controller
@RequestMapping("/admin/community")
public class AdminCommunityReviewController {

	@RequestMapping("/review/write")
	public ModelAndView review_list(){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/community/review/review_write");
		return mav;
	}
	
	
}
