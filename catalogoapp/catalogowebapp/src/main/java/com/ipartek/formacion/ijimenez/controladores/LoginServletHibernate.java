package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.ijimenez.dal.UsuarioDAOHibernate;
import com.ipartek.formacion.ijimenez.tipos.Carrito;
import com.ipartek.formacion.ijimenez.tipos.HibernateUsuario;

@WebServlet("/loginHibernate")
public class LoginServletHibernate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/* package */static final String RUTA = "/WEB-INF/vistas/";
	private static final String RUTA_PRINCIPAL = RUTA + "principal.jsp";
	private static final String RUTA_LOGIN = RUTA + "login.jsp";

	/* package */static final int MINIMO_CARACTERES = 4;

	private static Logger log = Logger.getLogger(LoginServletMySQL.class);

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
	public EntityManager manager = emf.createEntityManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Esto es el login servlet de HIBERNATE");

		UsuarioDAOHibernate usuarioDAO = null;

		try {
			// Llamada a l�gica de negocio
			ServletContext application = request.getServletContext();
			usuarioDAO = (UsuarioDAOHibernate) application.getAttribute("usuariosdal");
			// usuarioDAO.abrir();

			// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
			// EntityManager manager = emf.createEntityManager();
			// manager.getTransaction().begin();

			HibernateUsuario usuario;

			// Recoger datos de vistas
			String nombre = request.getParameter("nombre");
			System.out.println(nombre + "nombre");
			String pass = request.getParameter("pass");
			System.out.println(pass + "pass");
			String opcion = request.getParameter("opcion");
			System.out.println(opcion + "opcion");
			// Crear modelos en base a los datos

			usuario = new HibernateUsuario();
			usuario.setUsername(nombre);
			usuario.setPassword(pass);

			// if (usuarioDAO == null) {
			// usuarioDAO = DALFactory.getUsuariosDAL();
			// }

			System.out.println(usuario);

			HttpSession session = request.getSession();

			// ESTADOS

			// boolean esValido;

			boolean sinParametros;

			boolean esUsuarioYaRegistrado;

			boolean quiereSalir;

			boolean nombreValido;
			boolean passValido;

			// esValido = usuarioDAO.validar(usuario);

			sinParametros = usuario.getUsername() == null;

			esUsuarioYaRegistrado = session.getAttribute("usuario") != null;

			quiereSalir = "logout".equals(opcion);

			nombreValido = usuario.getUsername() != null && usuario.getUsername().length() >= MINIMO_CARACTERES;
			passValido = !(usuario.getPassword() == null || usuario.getPassword().length() < MINIMO_CARACTERES);

			// System.out.println(usuario + " usuario en loginservlet");
			// Redirigir a una nueva vista
			if (quiereSalir) {
				session.invalidate();
				log.info("Sesion de usuario cerrada");
				request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
			} else if (esUsuarioYaRegistrado) {
				log.info("Usuario registrado");
				request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request, response);
			} else if (sinParametros) {
				log.info("Sin parametros");
				request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
			} else if (!nombreValido || !passValido) {
				usuario.setErrores("El nombre y la pass deben tener como minimo " + MINIMO_CARACTERES + " caracteres y son ambos requeridos");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
			} else if (usuarioDAO.validar(usuario)) {
				System.out.println("SIGUE AQUI");
				System.out.println(usuario);
				System.out.println(usuarioDAO);
				usuario = (HibernateUsuario) usuarioDAO.findByUsername(usuario.getUsername());

				Carrito carrito = new Carrito();
				carrito = (Carrito) session.getAttribute("carrito");
				carrito.setIdUsuario(usuario.getId());
				session.setAttribute("carrito", carrito);
				session.setAttribute("usuario", usuario);
				System.out.println(usuario + " antes de la redireccion");
				// response.sendRedirect("principal.jsp");
				log.info("Sesion de usuario iniciada");
				request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request, response);
			} else {
				usuario.setErrores("El usuario y contraseña introducidos no son validos");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
			}

		} catch (Exception e) {
			System.out.println("HA CASCADO EL LOGIN");
			e.printStackTrace();
		} finally {
			// emf.close();
		}
	}
}
