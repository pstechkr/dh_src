package tiein.method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserLoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		//session check
		HttpSession session = request.getSession(false);
		
		// Referer
		String referer = request.getHeader("Referer");
		if(referer == null){
			referer = request.getSession().getServletContext().getContextPath(); 
		}
		System.out.println(referer);
		
		
		if(session == null){
			// 처리를 끝냄 - 컨트롤 요청이 가지 않음
			System.out.println("user session null");
			response.sendRedirect(referer);
			return false;
		}
		
		String user_seq = (String)session.getAttribute("user_seq");
		
		if(user_seq == null)
		{
			System.out.println("user_seq null");
			response.sendRedirect(referer);
			return false;
		}
		
		System.out.println("handle true");
		return true;

	}
}
