package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ijimenez.dal.DALFactory;
import com.ipartek.formacion.ijimenez.dal.UsuariosDAL;
import com.ipartek.formacion.ijimenez.tipos.Usuario;
import com.ipartek.formacion.ijimenez.tipos.Usuario.Nivel;

@WebServlet("/usuariocrud")
public class UsuarioCRUDServlet extends HttpServlet {
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/usuarioform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/usuariocrud";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		UsuariosDAL dal = (UsuariosDAL) application.getAttribute("dal");

		if (dal == null) {
			dal = DALFactory.getUsuariosDAL();

			dal.alta(new Usuario("usuario1", "pass1", Nivel.ADMIN));
			dal.alta(new Usuario("usuario2", "pass2", Nivel.USUARIO));

			application.setAttribute("dal", dal);
		}

		String op = request.getParameter("op");

		if (op == null) {

			Usuario[] usuarios = dal.buscarTodosLosUsuarios();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
		} else {
			String id = request.getParameter("id");

			Usuario usuario;

			switch (op) {
			case "modificar":
			case "borrar":
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
