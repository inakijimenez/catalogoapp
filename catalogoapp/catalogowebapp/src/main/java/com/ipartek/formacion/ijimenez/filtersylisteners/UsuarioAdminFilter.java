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

import com.ipartek.formacion.ijimenez.tipos.Usuario;
import com.ipartek.formacion.ijimenez.tipos.Usuario.Nivel;

/**
 * Servlet Filter implementation class UsuarioAdminFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/usuariocrud" })
public class UsuarioAdminFilter implements Filter {

	public UsuarioAdminFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("Estamos en el filtro");

		HttpSession session = ((HttpServletRequest) request).getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null || Nivel.ADMIN != usuario.getNivel()) {
			((HttpServletResponse) response).sendRedirect("index");
		}
		// else {
		// request.getRequestDispatcher("usuariocrud").forward(request, response);
		// }
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
