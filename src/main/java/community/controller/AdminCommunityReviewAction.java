package community.controller;

import javax.servlet.http.HttpServletRequest;
import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import community.model.ReviewInfoModel;

@Controller
@RequestMapping("/admin/community")
public class AdminCommunityReviewAction {

	@RequestMapping("/review/writeRequest")
	public ModelAndView review_list(@ModelAttribute ReviewInfoModel reviewInfoModel, HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		String reviewMapper = "community.mapper.AdminReviewMapper.";	// db setting 
		boolean isCheck = false;

		System.out.println(reviewInfoModel.getReview_name());
		System.out.println(reviewInfoModel.getReview_division());
		System.out.println(reviewInfoModel.getReview_time());
		System.out.println(reviewInfoModel.getReview_price());
		System.out.println(reviewInfoModel.getReview_parking());
		System.out.println(reviewInfoModel.getReview_main_image());
		System.out.println(reviewInfoModel.getReview_slide_image());
		System.out.println(reviewInfoModel.getReview_longitude());
		System.out.println(reviewInfoModel.getReview_latitude());
		System.out.println(reviewInfoModel.getReview_localtitle());
		System.out.println(reviewInfoModel.getReview_localname1());
		System.out.println(reviewInfoModel.getReview_localname2());
		System.out.println(reviewInfoModel.getReview_localname3());
		System.out.println(reviewInfoModel.getReview_localname4());
		
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try{
			
			session.insert(reviewMapper + "insert_reviewInfo", reviewInfoModel);
			session.commit();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		// 성공시 list로 리다이렉트
		if(isCheck){
			System.out.println("success");
			String redirectURL = request.getSession().getServletContext().getContextPath();
			redirectURL += "/admin/community/review/list";
			return new ModelAndView(new RedirectView(redirectURL));
		}
		
		return mav;
		
		
	}		
}
