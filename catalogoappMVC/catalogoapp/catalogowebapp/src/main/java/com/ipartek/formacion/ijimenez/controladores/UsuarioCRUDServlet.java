package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.ijimenez.dal.UsuariosDAL;
import com.ipartek.formacion.ijimenez.tipos.Usuario;

@WebServlet("/usuariocrud")
public class UsuarioCRUDServlet extends HttpServlet {
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/usuarioform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/usuariocrud";

	private static Logger log = Logger.getLogger(UsuarioCRUDServlet.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		UsuariosDAL dal = (UsuariosDAL) application.getAttribute("usuariosdal");

		// if (dal == null) {
		// dal = DALFactory.getUsuariosDAL();
		//
		// dal.alta(new Usuario("usuario1", "pass1", Nivel.ADMIN));
		// dal.alta(new Usuario("usuario2", "pass2", Nivel.USUARIO));
		//
		// application.setAttribute("usuariosdal", dal);
		// }

		String op = request.getParameter("op");

		if (op == null) {

			Usuario[] usuarios = dal.buscarTodosLosUsuarios();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
			return;
		} else {
			String id = request.getParameter("id");

			Usuario usuario;

			switch (op) {
			case "borrar":
				usuario = dal.buscarPorId(id);
				log.info("Se va a llevar a cabo la operacion de borrar un usuario");
				// request.setAttribute("usuario", usuario);
				dal.borrar(usuario);
				// request.getRequestDispatcher(RUTA_SERVLET_LISTADO).forward(request, response);
				response.sendRedirect(RUTA_SERVLET_LISTADO);
				break;
			case "modificar":
				usuario = dal.buscarPorId(id);
				request.setAttribute("usuario", usuario);
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
				break;
			default:
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
			}
		}
	}

}
