package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.ijimenez.dal.UsuarioDAOMySQL;
import com.ipartek.formacion.ijimenez.tipos.Usuario;
import com.ipartek.formacion.ijimenez.tipos.UsuarioMySQL;

@WebServlet("/usuariocrudMySQL")
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
		UsuarioDAOMySQL usuarioDAO = null;

		try {
			ServletContext application = request.getServletContext();
			usuarioDAO = (UsuarioDAOMySQL) application.getAttribute("usuariosdal");
			usuarioDAO.abrir();

			String op = request.getParameter("op");

			if (op == null) {

				// Usuario[] usuarios = dal.buscarTodosLosUsuarios();
				List<Usuario> usuarios = usuarioDAO.findAll();

				request.setAttribute("usuarios", usuarios);

				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
				return;
			} else {

				UsuarioMySQL usuario;
				System.out.println(request);
				usuario = (UsuarioMySQL) usuarioDAO.findByUsername(request.getParameter("id"));
				System.out.println(usuario);

				switch (op) {
				case "borrar":
					// usuario = usuarioDAO.findById(id);
					log.info("Se va a llevar a cabo la operacion de borrar un usuario");
					// request.setAttribute("usuario", usuario);
					usuarioDAO.delete(usuario);
					// request.getRequestDispatcher(RUTA_SERVLET_LISTADO).forward(request, response);
					response.sendRedirect(RUTA_SERVLET_LISTADO);
					break;
				case "modificar":
					// usuario = usuarioDAO.findById(id);
					request.setAttribute("usuario", usuario);
				case "alta":
					request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
					break;
				default:
					request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
				}
			}
		} catch (Exception e) {
			System.out.println("HA CASCADO USUARIOCRUD");
			e.printStackTrace();
		} finally {
			usuarioDAO.cerrar();
		}
	}
}
