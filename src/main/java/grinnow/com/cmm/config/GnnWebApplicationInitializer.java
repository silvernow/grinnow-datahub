package grinnow.com.cmm.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;

import grinnow.com.cmm.filter.CorsFilter;
import grinnow.com.cmm.service.GnnProperties;

public class GnnWebApplicationInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(GnnWebApplicationInitializer.class);
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		LOGGER.debug("Gnn DataHub ApplicationInitializer START-============================================");
		LOGGER.info("Loading profile: [" + GnnProperties.getProperty("system.config.profile") + "]");
		
		//-------------------------------------------------------------
		// Web ServletContextListener 설정
		//-------------------------------------------------------------
		servletContext.addListener(new grinnow.com.cmm.context.WebServletContextListener());

		//-------------------------------------------------------------
		// Spring CharacterEncodingFilter 설정
		//-------------------------------------------------------------
		FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("encodingFilter", new org.springframework.web.filter.CharacterEncodingFilter());
		characterEncoding.setInitParameter("encoding", "UTF-8");
		characterEncoding.setInitParameter("forceEncoding", "true");
		characterEncoding.addMappingForUrlPatterns(null, false, "/*");
		
		//-------------------------------------------------------------
		// Spring ServletContextListener 설정
		//-------------------------------------------------------------
		XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
		rootContext.setConfigLocations(new String[] { "classpath*:grinnow/spring/com/**/context-*.xml" });
		rootContext.refresh();
		rootContext.start();
		
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		//-------------------------------------------------------------
		// Spring ServletContextListener 설정
		//-------------------------------------------------------------
		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
		xmlWebApplicationContext.setConfigLocation("/WEB-INF/config/grinnow/springmvc/grinnow-com-*.xml");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(xmlWebApplicationContext));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
		
		MultipartFilter springMultipartFilter = new MultipartFilter();
		springMultipartFilter.setMultipartResolverBeanName("multipartResolver");
		FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("springMultipartFilter", springMultipartFilter);
		multipartFilter.addMappingForUrlPatterns(null, false, "/*");
		
		FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", new CorsFilter());
		corsFilter.addMappingForUrlPatterns(null, false, "/*");
		
		//-------------------------------------------------------------
		// Spring RequestContextListener 설정
		//-------------------------------------------------------------
		servletContext.addListener(new org.springframework.web.context.request.RequestContextListener());
		
		addJspServletInitParam(servletContext);

		LOGGER.debug("GnnWebApplicationInitializer END-============================================");
	}
	
	private void addJspServletInitParam(ServletContext servletContext) {
	    ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
	    jspServlet.setInitParameter("trimSpaces", "true");
	    jspServlet.setInitParameter("async-supported", "true");
	}
}
