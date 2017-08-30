package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.ijimenez.dal.DAOException;
import com.ipartek.formacion.ijimenez.dal.UsuarioDAOHibernate;
import com.ipartek.formacion.ijimenez.tipos.HibernateUsuario;

@WebServlet("/usuarioform")
public class UsuarioFormHibernateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UsuarioFormServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UsuarioDAOHibernate usuarioDAO = null;

		String op = request.getParameter("opform");

		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		String nombre_completo = request.getParameter("nombre_completo");
		int id;
		int nivel = 2;

		try {

			ServletContext application = request.getServletContext();
			usuarioDAO = (UsuarioDAOHibernate) application.getAttribute("usuariosdal");
			// usuarioDAO.abrir();

			if (request.getParameter("nivel").equals("usuario")) {
				// request.setAttribute("nivel", 2);
				nivel = 2;
			} else if (request.getParameter("nivel").equals("admin")) {
				// request.setAttribute("nivel", 1);
				nivel = 1;
			}

			RequestDispatcher rutaListado = request.getRequestDispatcher(UsuarioCRUDServlet.RUTA_SERVLET_LISTADO);
			RequestDispatcher rutaFormulario = request.getRequestDispatcher(UsuarioCRUDServlet.RUTA_FORMULARIO);

			// response.setContentType("text/plain");
			// PrintWriter out = response.getWriter();
			// out.println(op);
			// out.println(nombre);
			// out.println(pass);
			// out.println(pass2);

			if (op == null) {
				rutaListado.forward(request, response);
				return;
			}

			HibernateUsuario usuario = new HibernateUsuario(nivel, nombre_completo, pass, username);

			System.out.println(usuario + "al cogerlo de la jsp");
			switch (op) {
			case "alta":
				log.info("Se va a llevar a cabo la operacion de alta un usuario");
				if (pass.equals(pass2)) {
					usuarioDAO.insert(usuario);
					response.sendRedirect(UsuarioCRUDServlet.RUTA_SERVLET_LISTADO);
				} else {
					usuario.setErrores("Las contraseñas no coinciden");
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
				}

				break;
			case "modificar":
				log.info("Se va a llevar a cabo la operacion de modificar un usuario");

				if (pass.equals(pass2)) {
					try {
						id = ((HibernateUsuario) usuarioDAO.findByUsername(username)).getId();
						usuario.setId(id);
						usuarioDAO.update(usuario);
					} catch (DAOException e) {
						usuario.setErrores(e.getMessage());
						request.setAttribute("usuario", usuario);
						rutaFormulario.forward(request, response);
						return;
					}
					response.sendRedirect(UsuarioCRUDServlet.RUTA_SERVLET_LISTADO);
				} else {
					usuario.setErrores("Las contraseñas no coinciden");
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
				}

				break;
			case "borrar":
				log.info("Se va a llevar a cabo la operacion de borrar un usuario");
				usuarioDAO.delete(usuario);
				response.sendRedirect(UsuarioCRUDServlet.RUTA_SERVLET_LISTADO);

				break;
			}

		} catch (Exception e) {
			System.out.println("HA CASCADO USUARIOFORM");
			e.printStackTrace();
		}
		// finally {
		// usuarioDAO.cerrar();
		// }
	}

}
