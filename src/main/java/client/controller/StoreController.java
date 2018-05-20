package client.controller;

import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import client.model.StoreInfoModel;

@Controller
@RequestMapping("/client")
public class StoreController {

	// 매장 관리 페이지
	@RequestMapping("/store")
	public ModelAndView storeManagement(HttpSession httpSession){

		// view setting
		ModelAndView mav = new ModelAndView();

		// model input setting
		StoreInfoModel storeInfoModel = new StoreInfoModel();
		storeInfoModel.setClient_seq((String)httpSession.getAttribute("client_seq"));
		storeInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));

		// db setting
		String storeMapper = "client.mapper.StoreMapper.";	

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			// client_seq & store_seq로 매장 정보를 불러온다.
			storeInfoModel = session.selectOne(storeMapper + "select_storeInfo", storeInfoModel);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		mav.addObject("storeInfoModel", storeInfoModel);
		mav.setViewName("/client/store");
		return mav;
	}
}
