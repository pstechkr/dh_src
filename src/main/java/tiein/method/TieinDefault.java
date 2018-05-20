package tiein.method;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;









//import javax.mail.Authenticator;
import javax.mail.Message;
//import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;






import org.springframework.web.util.CookieGenerator;

import service.model.CouponInfoModel;
import user.model.UserInfoModel;




public class TieinDefault {

	/*
	 * 비밀번호찾기 관련 메소드 모음
	 */
	
	public void MailSend(String recipient, String newPassword){
		
		String subject = "엘루비 :: 새로운 비밀번호 안내 메일입니다.";// 제목
		String body = "신규 비밀번호는 " + newPassword + " 입니다."  ; // 내용
		
		postMail(subject, body, recipient);
		
	}
	
	
	private void postMail(String subject, String body, String recipient){
		
		// 메일 관련 정보 (naver works)
//        String host = "dsmtp.naver.com"; 
		String host = "smtp.works.naver.com";
        final String username = "elever_mail@tiein.co.kr";
        final String password = "tiein2012";
        int port=465;
        
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un=username;
            String pw=password;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(un, pw);
            }
        });
        session.setDebug(true); //for debug
        
        
        
        // send
        try{
        	
	        Message mimeMessage = new MimeMessage(session);
	        mimeMessage.setFrom(new InternetAddress("elever@tiein.co.kr"));
	        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	        mimeMessage.setSubject(subject);
	        mimeMessage.setText(body);
	        Transport.send(mimeMessage);
        }catch(Exception e){
        	System.out.println(recipient + "에게 보내는 메일 발송 실패.");
        }
	}
	
	/*
	 * 
	 *  랜덤 숫자 생성
	 *  
	 */
    
	public String randomNum(int len){
		
		int num = 0;
		String numStr = "";
		
		Random r= new Random();
		
		for(int x=0; x<len;  x++){
			num = r.nextInt(10);
			numStr = numStr + num+"";
		}
		return numStr;
	}
	
	/*
	 * 
	 *  비밀번호 암호화
	 *   
	 */
	public String passSHA(String inPw){
		byte[] pwd = inPw.getBytes();
		MessageDigest md = null;
		
		try
		{
			md = MessageDigest.getInstance("SHA-1");
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		if(md==null)
			return null;
		
		md.reset();
		md.update(pwd);
		byte digest[] = md.digest();
		
		StringBuffer buffer = new StringBuffer();
		
		for (int i=0; i < digest.length; i++)
		{
			buffer.append(Integer.toHexString(0xFF & digest[i]));
		}
		return buffer.toString();
	}

	
	
	/*
	 * 
	 *  공통 :: 로그인 세션 체크 (로그인이 되어있는지 체크)
	 *  
	 */
	public String loginSessionCheck(HttpSession httpsession){

		String sessionCheck = "N";
		if(httpsession.getAttribute("user_seq") != null){
			sessionCheck = "Y";
		}

		return sessionCheck;
	}
	
	
	
	/*
	 * 
	 *  공통 :: 로그인 하여 성공했는지 체크
	 *  
	 */
	public boolean loginCheck(UserInfoModel userInfoModel, HttpSession httpsession){
		
		// sql setting
		String userMapper = "user.mapper.UserMapper.";

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			// 페북 로그인인지 일반 로그인인지 판별.
			if(userInfoModel.getFacebook_id() == null){
				// 비밀번호 암호화
				userInfoModel.setUser_password(passSHA(userInfoModel.getUser_password()));
				userInfoModel = session.selectOne(userMapper + "select_userInfoLogin", userInfoModel);
			}else{
				userInfoModel = session.selectOne(userMapper +"select_userInfoLogin_facebook", userInfoModel);
			}
			
			if(userInfoModel.isSuccess()){
				// session setting
				httpsession.setAttribute("user_name", userInfoModel.getUser_name());
				httpsession.setAttribute("user_seq", userInfoModel.getUser_seq());
				
				// 최근 로그인 이력 변경
				session.update(userMapper + "update_userInfo_recentLogin", userInfoModel);
				session.commit();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return userInfoModel.isSuccess();
		
	}
	
	
	/*
	 * 
	 *  공통 :: 페이스북 로그인 및 페이스북 회원가입 로직 
	 *  
	 */
	public boolean facebookLogin(UserInfoModel userInfoModel, HttpSession httpsession){
		
		boolean isSuccess = false;
		String couponMapper = "service.mapper.CouponMapper.";
		
		// sql setting
		String userMapper = "user.mapper.UserMapper.";
		
		if(!userInfoModel.getUser_gender().equals("")){
			if(userInfoModel.getUser_gender().equals("male")){userInfoModel.setUser_gender("0");}
			else{userInfoModel.setUser_gender("1");}
		}
		if(!userInfoModel.getUser_birthday().equals("")){
			String[] tmp = userInfoModel.getUser_birthday().split("/");
			String birthday = tmp[2] + "-" + tmp[0] + "-" + tmp[1];
			userInfoModel.setUser_birthday(birthday);
		}
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		try
		{	
			Map<String, String> facebookMap = new HashMap<String, String>();
			facebookMap.put("facebook_id", userInfoModel.getFacebook_id());
			facebookMap.put("user_email", userInfoModel.getUser_email());
			
			// 페북용 email 주소와 동일한 것이 있는가? (중복확인) 및 페이스북 아이디 연동 여부 판별
			facebookMap = session.selectOne(userMapper + "select_email_check_facebook", facebookMap);
			
			// 동일한 것이 있다면, 로그인 혹은 기존의 것과 연동 후 로그인.
			if(facebookMap.get("isCheck").equals("true")){
				
				// facebook_id도 있으므로 로그인.
				if(facebookMap.get("facebook_id") != null){
					isSuccess = loginCheck(userInfoModel, httpsession);
					
				}else{
				// facebook_id가 없으므로 서로 연동.
					session.update(userMapper + "update_userInfoRegister_facebookid_facebook", userInfoModel);
				}
				
			// 페북용 email 주소와 동일한 것이 없는 경우.
			}else{
				
				facebookMap.put("facebook_id", userInfoModel.getFacebook_id());
				facebookMap.put("user_email", userInfoModel.getUser_email());
				
				// 이 중에서, 페이스북 email을 나중에 바꾸는 경우의 수도 존재한다.
				// 그러므로, 기존의 facebook id가 있는지 체크하고, 있다면 이메일 주소를 갱신한다.
				facebookMap = session.selectOne(userMapper + "select_facebookid_check_facebook", facebookMap);
				
				if(facebookMap.get("isCheck").equals("true")){
					// facebook_id가 있으므로, 이메일 정보 갱신.
					session.update(userMapper + "update_userInfoRegister_email_facebook", userInfoModel);
					
				}else{
					// facebook id가 없으므로, 
					// 완전 신규가입.
					session.insert(userMapper + "insert_userInfoRegister_facebook", userInfoModel);
					
					// 회원가입 쿠폰 등록 
					CouponInfoModel couponInfoModel = couponCreate("엘루비 오픈 기념 할인(5000원)",3,"5000","0", userInfoModel.getUser_seq());
					session.insert(couponMapper + "insert_couponInfo", couponInfoModel);
				}
			}
			session.commit();
			
			// 바로 로그인 외에는 insert,update 되었으므로, 다시 로그인 로직을 탄다.
			if(!isSuccess){
				isSuccess = loginCheck(userInfoModel, httpsession);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return isSuccess;
		
	}
	
	
	
	
	/*
	 * 
	 *  페이징처리
	 *  
	 *  
	 */
	public Map<String, Object> pagination(String total_count, String row, int pagination_row, int page){
		
		int total_pagination = (int)Math.ceil(Double.parseDouble(total_count)/Double.parseDouble(row));
		int divide_pagination = (int)Math.ceil(Math.ceil(Double.parseDouble(total_count)/Double.parseDouble(row))/pagination_row);
		
		int last_pagination = 1; // 해당 divide_pagination에서의 마지막 페이지 번호 
		int first_pagination = 1; // 해당 divide_pagination에서의 첫번째 페이지 번호
		
		// ex divide_pagination 2, pagination_row 2이라면,
		for(int j=1; j <= divide_pagination; j++){
		
			//ex j=1 ;  first 1 last 2 	//ex j=2 ;  first 3 last 4
			int last = j * pagination_row;
			int first = last - pagination_row + 1;
			// 그런데 마지막 루프인 경우에는 last = j * pagination_row 가 아닐 수 있음
			if(j == divide_pagination){last = total_pagination;}
			
			// 구분된 수 사이에 _page가 있다면.
			if(first <= page && page <= last){
				first_pagination = first;
				last_pagination = last;
			}
		}
		
		// mav에 담을 때, pagination : 번호 전체.
		// this_page : 현재 페이지 
		// last_page : 마지막 페이지
		List<Integer> pagination = new ArrayList<Integer>();
		while(first_pagination <= last_pagination){
			pagination.add(first_pagination);
			first_pagination ++;
		}
		
		Map<String, Object> output = new HashMap<String, Object>(); 
		output.put("pagination", pagination);
		output.put("total_pagination", total_pagination);
		
		return output;
	}
	
	/*
	 * 
	 * 쿠폰 발급
	 * 
	 * 
	 */
	
	public CouponInfoModel couponCreate(String name, int limit, String price, String div, String user_seq){
		
		// 쿠폰 생성
		CouponInfoModel couponInfoModel = new CouponInfoModel();
		couponInfoModel.setCoupon_name(name);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		String now = format1.format(c.getTime());
		c.add(Calendar.MONTH, limit); // 3개월
		String toDate = format.format(c.getTime());
		couponInfoModel.setCoupon_limit(toDate);
		couponInfoModel.setCoupon_price(price);
		couponInfoModel.setCoupon_div(div);
		couponInfoModel.setUser_seq(user_seq);
		couponInfoModel.setCoupon_id(now + randomNum(7));

		return couponInfoModel;
	}
	
	public CouponInfoModel couponCreate(String name, String limit, String price, String div, String user_seq){
		
		// 쿠폰 생성
		CouponInfoModel couponInfoModel = new CouponInfoModel();
		couponInfoModel.setCoupon_name(name);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		String now = format1.format(c.getTime());
//		c.add(Calendar.MONTH, limit); // 3개월
//		String toDate = format.format(c.getTime());
		couponInfoModel.setCoupon_limit(limit);
		couponInfoModel.setCoupon_price(price);
		couponInfoModel.setCoupon_div(div);
		couponInfoModel.setUser_seq(user_seq);
		couponInfoModel.setCoupon_id(now + randomNum(7));

		return couponInfoModel;
	}


	/*
	 * 
	 * 공통 :: 조회수(쿠키) 체크 
	 * 
	 */
	
	public boolean cookieCheck(String name, String seq, HttpServletRequest request, HttpServletResponse response){
		
		CookieGenerator cookieGer = new CookieGenerator();
		cookieGer.setCookieName(name + "_" + seq);
		cookieGer.addCookie(response, "checked");
		boolean count_key = true;
		System.out.println("cookieger : " + cookieGer.getCookieName());
		
		// 쿠키를 이용한 조회수 체크 및 추가 
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0){
			
			for(Cookie cookie: cookies){
				System.out.println("cookie   :" + cookie.getName());
				if(cookieGer.getCookieName().equals(cookie.getName())){
					// 이미 읽음 
					count_key = false;
					System.out.println("이미 읽음");
				}
			}
		}
		
		return count_key;
	}
}