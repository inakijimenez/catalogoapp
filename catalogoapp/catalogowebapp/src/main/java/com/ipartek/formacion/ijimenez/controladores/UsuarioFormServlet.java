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

import com.ipartek.formacion.ijimenez.dal.DALException;
import com.ipartek.formacion.ijimenez.dal.UsuariosDAL;
import com.ipartek.formacion.ijimenez.tipos.Usuario;
import com.ipartek.formacion.ijimenez.tipos.Usuario.Nivel;

@WebServlet("/usuarioform")
public class UsuarioFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UsuarioFormServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("opform");

		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		if (request.getParameter("nivel").equals("usuario")) {
			request.setAttribute("nivel", Nivel.USUARIO);
		} else if (request.getParameter("nivel").equals("admin")) {
			request.setAttribute("nivel", Nivel.ADMIN);
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

		Usuario usuario = new Usuario(nombre, pass, Nivel.USUARIO);

		ServletContext application = request.getServletContext();
		UsuariosDAL dal = (UsuariosDAL) application.getAttribute("dal");

		switch (op) {
		case "alta":
			log.info("Se va a llevar a cabo la operacion de alta un usuario");
			if (pass.equals(pass2)) {
				dal.alta(usuario);
				rutaListado.forward(request, response);
			} else {
				usuario.setErrores("Las contrase�as no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "modificar":
			log.info("Se va a llevar a cabo la operacion de modificar un usuario");
			if (pass.equals(pass2)) {
				try {
					dal.modificar(usuario);
				} catch (DALException de) {
					usuario.setErrores(de.getMessage());
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
					return;
				}
				rutaListado.forward(request, response);
			} else {
				usuario.setErrores("Las contrase�as no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "borrar":
			log.info("Se va a llevar a cabo la operacion de borrar un usuario");
			dal.borrar(usuario);
			rutaListado.forward(request, response);

			break;
		}
	}

}
