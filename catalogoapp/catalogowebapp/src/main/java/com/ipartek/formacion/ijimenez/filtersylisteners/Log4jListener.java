package com.ipartek.formacion.ijimenez.filtersylisteners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

@WebListener
public class Log4jListener implements ServletContextListener {

	public Log4jListener() {

	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		// Configure Log4J
		PropertyConfigurator.configure(sce.getServletContext().getRealPath("/WEB-INF/classes") + "/log4j.properties");
	}
}
