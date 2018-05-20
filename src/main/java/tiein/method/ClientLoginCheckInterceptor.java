package tiein.method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ClientLoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		//session check
		HttpSession session = request.getSession(false);
		
		
		if(session == null){
			// 처리를 끝냄 - 컨트롤 요청이 가지 않음
			response.sendRedirect("index");
			System.out.println("client session null");
			return false;
		}
		
		String client_seq = (String)session.getAttribute("client_seq");
		if(client_seq == null)
		{
			response.sendRedirect("index");
			System.out.println("client_seq null");
			return false;
		}
		System.out.println("handle true");
		return true;

	}
}
