package admin.controller;

import javax.servlet.http.HttpServletRequest;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import service.model.SliderInfoModel;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value={"/index"})
	public ModelAndView join(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/admin/index");
	
		return mav;
	}
	
	@RequestMapping(value={""})
	public ModelAndView redirect(HttpServletRequest request){
		String redirectURL = request.getSession().getServletContext().getContextPath() + "/admin/index";
		return new ModelAndView(new RedirectView(redirectURL));
	}
	
	@RequestMapping("/slider")
	public ModelAndView slider(){
		
		ModelAndView mav = new ModelAndView();
		SliderInfoModel sliderInfoModel = new SliderInfoModel();
		String sliderMapper = "service.mapper.SliderMapper.";	// db setting
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			sliderInfoModel = session.selectOne(sliderMapper + "select_sliderInfo");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		mav.setViewName("/admin/slider/slider");
		mav.addObject("sliderInfoModel", sliderInfoModel);
		return mav;
		
	}
}
