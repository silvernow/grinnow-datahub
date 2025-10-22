package grinnow.com.cmm.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import grinnow.com.cmm.service.GnnProperties;

public class WebServletContextListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebServletContextListener.class);
    
    public WebServletContextListener(){
    	setProfileSetting();
    }

    public void contextInitialized(ServletContextEvent event){
    	if(System.getProperty("spring.profiles.active") == null){
    		setProfileSetting();
    	}
    }

    public void contextDestroyed(ServletContextEvent event) {
    	if(System.getProperty("spring.profiles.active") != null){
    		System.setProperty("spring.profiles.active", null);
    	}
    }
    
    public void setProfileSetting(){
        try {
            LOGGER.debug("===========================Start ServletContextLoad START ===========");
            System.setProperty("spring.profiles.active", GnnProperties.getProperty("Globals.DbType")+","+GnnProperties.getProperty("Globals.Auth"));
            LOGGER.debug("===========================END   ServletContextLoad END ===========");
        } catch(IllegalArgumentException e) {
    		LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch (Exception e) {
        	LOGGER.error("[" + e.getClass() +"] search fail : " + e.getMessage());
        }
    }
}
