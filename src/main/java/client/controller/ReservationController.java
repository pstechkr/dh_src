package client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import client.model.MenuInfoModel;
import client.model.StoreInfoModel;

@Controller
@RequestMapping("/client")
public class ReservationController {
	
	// 예약관리 페이지 첫화면
	@RequestMapping("/reservation")
	public ModelAndView reservation(HttpSession httpSession){
		
		// view setting
		ModelAndView mav = new ModelAndView();
		
		// db model setting
		StoreInfoModel storeInfoModel = new StoreInfoModel();
		storeInfoModel.setClient_seq((String)httpSession.getAttribute("client_seq"));
		storeInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));
		
		MenuInfoModel menuInfoModel = new MenuInfoModel();
		menuInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));
		List<MenuInfoModel> menuList = null; 
		
		// db setting
		String storeMapper = "client.mapper.StoreMapper.";
		String MenuMapper = "client.mapper.MenuMapper.";
		

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			
			// store(reservation), menu 정보를 검색해온다.
			storeInfoModel = session.selectOne(storeMapper + "select_reservationInfo", storeInfoModel);
			menuList = session.selectList(MenuMapper + "select_menuInfo", menuInfoModel);
		
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		mav.addObject("storeInfoModel", storeInfoModel);
		mav.addObject("menuList", menuList);
		mav.setViewName("/client/reservation");
		return mav;
	}
	
}
