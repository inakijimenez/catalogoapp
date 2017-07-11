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
 * Servlet Filter implementation class CarritoLoginFilter
 */

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD }, filterName = "CheckoutFilter", urlPatterns = { "/checkout" })
public class CheckoutFilter implements Filter {

	private static Logger log = Logger.getLogger(CheckoutFilter.class);

	public CheckoutFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		log.trace("Estamos en el filtro de usuario logeado del checkout");

		HttpSession session = ((HttpServletRequest) request).getSession();

		UsuarioMySQL usuario = (UsuarioMySQL) session.getAttribute("usuario");

		if (usuario != null) {
			log.info("El usuario es " + usuario + "y su nivel de acceso es " + usuario.getId_roles());
		} else {
			log.info("El usuario no esta logeado");
		}

		if (usuario == null) {
			((HttpServletResponse) response).sendRedirect("login");
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
