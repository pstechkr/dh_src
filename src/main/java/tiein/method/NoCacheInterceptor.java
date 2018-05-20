package tiein.method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class NoCacheInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		
		 response.setHeader("Pragma", "no-cache"); 
		 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		 response.setDateHeader("Expires", 0);
		 
	}
}
