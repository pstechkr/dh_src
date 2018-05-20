package client.controller;

import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import client.model.StoreInfoModel;

@Controller
public class StoreAction {
	// 매장 관리 페이지 내용 수정
	@RequestMapping(value = "/client/modify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody StoreInfoModel modify(@RequestBody StoreInfoModel storeInfoModel, HttpSession httpSession){
		
		// model setting
		storeInfoModel.setClient_seq((String)httpSession.getAttribute("client_seq"));
		storeInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));
		storeInfoModel.setModifyYN("N");

		// db setting
		String storeMapper = "client.mapper.StoreMapper.";
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			session.update(storeMapper + "update_storeInfoModify", storeInfoModel);
			session.commit();
			storeInfoModel.setModifyYN("Y");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}

		// 리턴 보내고.
		return storeInfoModel;
	}
}
