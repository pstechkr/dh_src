package tiein.method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserNotLoginCheckInterceptor extends HandlerInterceptorAdapter {

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
		
		String user_seq = (String)session.getAttribute("user_seq");
		
		if(user_seq != null)
		{
			System.out.println("user_seq not null");
			response.sendRedirect(referer);
			return false;
		}
		
		System.out.println("handle true");
		return true;

	}
}
