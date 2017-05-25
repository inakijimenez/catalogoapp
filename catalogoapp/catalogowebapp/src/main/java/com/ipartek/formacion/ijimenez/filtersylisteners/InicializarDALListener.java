package com.ipartek.formacion.ijimenez.filtersylisteners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InicializarDALListener implements ServletContextListener {

	public InicializarDALListener() {

	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		// Inicializar DAL y Tablas
	}

}
