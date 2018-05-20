package user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import service.model.CouponInfoModel;
import tiein.method.TieinDefault;
import user.model.UserInfoModel;

@Controller
public class UserAction {
	
	// 이메일 중복 체크
	@RequestMapping(value = "/email_check", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> email_check(@RequestBody Map<String, String> INPUT){
		
		Map<String, Object> OUTPUT = new HashMap<String, Object>();
		
		// sql setting
		String userMapper = "user.mapper.UserMapper.";	// db setting 
		boolean isCheck = false;

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			isCheck = session.selectOne(userMapper + "select_email_check", INPUT);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		OUTPUT.put("isCheck", isCheck);
		return OUTPUT;
	}
	
	// 회원가입
	@RequestMapping("/register")
	public ModelAndView register(@ModelAttribute UserInfoModel userInfoModel, HttpServletRequest request){
		
		// sql setting
		String userMapper = "user.mapper.UserMapper.";
		String couponMapper = "service.mapper.CouponMapper.";
		boolean isCheck = false;
		
		// 비밀번호 암호화
		TieinDefault tieinDefault = new TieinDefault();
		userInfoModel.setUser_password(tieinDefault.passSHA(userInfoModel.getUser_password()));

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			session.insert(userMapper + "insert_userInfoRegister", userInfoModel);
			// 회원가입 쿠폰 등록 
			CouponInfoModel couponInfoModel = tieinDefault.couponCreate("엘루비 오픈 기념 할인(5000원)",3,"5000","0", userInfoModel.getUser_seq());
			session.insert(couponMapper + "insert_couponInfo", couponInfoModel);
			session.commit();
			isCheck = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}

		if(isCheck){
			String redirect = request.getSession().getServletContext().getContextPath() + "/login";
			ModelAndView mav = new ModelAndView("alert");
			mav.addObject("msg","회원 가입이 완료되었습니다!");
			mav.addObject("func","location.href='" + redirect + "';");
			return mav;
		}
		
		String redirectURL = request.getSession().getServletContext().getContextPath();
		return new ModelAndView(new RedirectView(redirectURL));
	}
	
	// 일반 로그인 창에서의 페이스북 가입 및 로그인
	@RequestMapping("/facebookLogin")
	public ModelAndView facebookRequest(@ModelAttribute UserInfoModel userInfoModel, HttpSession httpsession, HttpServletRequest request){
		
		TieinDefault tieinDefault = new TieinDefault();
		boolean isSuccess = tieinDefault.facebookLogin(userInfoModel, httpsession);
		
		if(isSuccess){
			String redirectURL = request.getSession().getServletContext().getContextPath();
			return new ModelAndView(new RedirectView(redirectURL));
		}
		
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	// 로그인
	@RequestMapping("/loginRequest")
	public ModelAndView loginRequest(@ModelAttribute UserInfoModel userInfoModel, HttpServletRequest request, HttpSession httpsession){

		TieinDefault tieinDefault = new TieinDefault();
		boolean isSuccess = tieinDefault.loginCheck(userInfoModel, httpsession);
		
		System.out.println("isSUCCESS 로그인 : " + isSuccess);
		
		if(!isSuccess){
			ModelAndView mav = new ModelAndView("alert");
			mav.addObject("msg", "아이디 혹은 비밀번호가 일치하지 않습니다.");
			mav.addObject("func", "history.go(-1);");
			return mav;
		}
		
		String redirectURL = request.getSession().getServletContext().getContextPath();
		return new ModelAndView(new RedirectView(redirectURL));
	}
	
	// 비밀번호 찾기
	@RequestMapping(value="/passwordRequest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> passwordRequest(@RequestBody Map<String, Object> map){
	
		// sql setting
		String userMapper = "user.mapper.UserMapper.";
		boolean isCheck = false;
		
		// 비밀번호 암호화 / 메일 보내기 클래스
		TieinDefault tieinDefault = new TieinDefault();
		
		// JSON 정렬
		Map<String, Object> rtnMap = new HashMap<String, Object>();
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			// 이메일이 있는지 검사
			isCheck = session.selectOne(userMapper + "select_email_check", map);

			if(!isCheck){
				rtnMap.put("isCheck", isCheck);
				rtnMap.put("msg", "존재하는 이메일이 아닙니다.");
				return rtnMap;
			}
			
			// 비밀번호 변경.
			String newPassword = tieinDefault.randomNum(8);
			String _newPassword = tieinDefault.passSHA(newPassword); // 암호화
			map.put("user_password", _newPassword);
			session.update(userMapper + "update_findpassword", map);
			session.commit();
			
			// 비밀번호 발송.
			rtnMap.put("isCheck", isCheck);
			tieinDefault.MailSend((String)map.get("user_email"), newPassword);
			rtnMap.put("msg", "메일이 성공적으로 보내졌습니다.");

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}

		// 리턴
		return rtnMap;
	}

}
