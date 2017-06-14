package com.ipartek.formacion.ijimenez.filtersylisteners;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.ijimenez.tipos.UsuarioMySQL;

/**
 * Servlet Filter implementation class UsuarioAdminFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/usuariocrud" })
public class UsuarioAdminFilter implements Filter {

	private static Logger log = Logger.getLogger(UsuarioAdminFilter.class);

	public UsuarioAdminFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		log.trace("Estamos en el filtro de administracion para usuarios");

		HttpSession session = ((HttpServletRequest) request).getSession();

		UsuarioMySQL usuario = (UsuarioMySQL) session.getAttribute("usuario");

		if (usuario != null) {
			log.info("El usuario es " + usuario + "y su nivel de acceso es " + usuario.getId_roles());
		} else {
			log.info("El usuario no esta logeado");
		}

		if (usuario == null || usuario.getId_roles() != 1) {
			((HttpServletResponse) response).sendRedirect("index");
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
