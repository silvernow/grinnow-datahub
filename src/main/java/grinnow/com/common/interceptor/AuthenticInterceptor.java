package grinnow.com.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticInterceptor.class);
	
	@Override 
	public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception {
	  
		LOGGER.info("Url ::: " + request.getRequestURL());
		return true;
	}
}
