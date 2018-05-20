package user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import service.model.CouponInfoModel;
import tiein.method.TieinDefault;

@Controller
@RequestMapping("/admin/user")
public class AdminUserAction {

	@RequestMapping("/couponRequest")
	public ModelAndView couponRequest(HttpServletRequest request){
		
		
		TieinDefault tieinDefault = new TieinDefault();
		
		String coupon_name = request.getParameter("coupon_name");
		String coupon_limit_div = request.getParameter("coupon_limit_div");
		String coupon_price = request.getParameter("coupon_price");
		String coupon_div = request.getParameter("coupon_div");
		String _user_seq = request.getParameter("user_seq");
		List<CouponInfoModel> list = new ArrayList<CouponInfoModel>();
		
		// 복수 user_seq
		if(_user_seq.contains("/")){
			String[] user_seq = _user_seq.split("/");
			
			// 0 = 지정 (string limit)
			if(coupon_limit_div.equals("0")){
				
				for(int i=0; i < user_seq.length; i++){
					String coupon_limit = request.getParameter("coupon_limit");
					CouponInfoModel couponInfoModel = tieinDefault.couponCreate(coupon_name, coupon_limit, coupon_price, coupon_div, user_seq[i]);
					list.add(couponInfoModel);
				}
			
			// 1 = 기간 (int limit)
			}else{
				for(int i=0; i < user_seq.length; i++){
					
					int coupon_limit = Integer.parseInt(request.getParameter("coupon_limit"));
					CouponInfoModel couponInfoModel = tieinDefault.couponCreate(coupon_name, coupon_limit, coupon_price, coupon_div, user_seq[i]);
					list.add(couponInfoModel);
				}
			}
		
		// 단일 user_seq
		}else{
			String user_seq = _user_seq;
			
			// 0 = 지정 (string limit)
			if(coupon_limit_div.equals("0")){
				String coupon_limit = request.getParameter("coupon_limit");
				CouponInfoModel couponInfoModel = tieinDefault.couponCreate(coupon_name, coupon_limit, coupon_price, coupon_div, user_seq);
				list.add(couponInfoModel);
			
			// 1 = 기간 (int limit)
			}else{
				int coupon_limit = Integer.parseInt(request.getParameter("coupon_limit"));
				CouponInfoModel couponInfoModel = tieinDefault.couponCreate(coupon_name, coupon_limit, coupon_price, coupon_div, user_seq);
				list.add(couponInfoModel);
			}
		}
		
		
		// sql
		String couponMapper = "service.mapper.CouponMapper.";	// db setting
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		try
		{
			session.insert(couponMapper + "insert_couponInfo_user", list);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		String redirectURL = request.getSession().getServletContext().getContextPath() + "/admin/user/list";
		return new ModelAndView(new RedirectView(redirectURL));
	}
}
