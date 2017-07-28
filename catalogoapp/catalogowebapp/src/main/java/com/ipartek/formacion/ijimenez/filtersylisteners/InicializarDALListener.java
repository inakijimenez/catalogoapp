package com.ipartek.formacion.ijimenez.filtersylisteners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.ijimenez.dal.DALFactory;
import com.ipartek.formacion.ijimenez.dal.FacturaDAO;
import com.ipartek.formacion.ijimenez.dal.ProductosDAL;
import com.ipartek.formacion.ijimenez.dal.UsuarioDAO;
import com.ipartek.formacion.ijimenez.tipos.Carrito;

@WebListener
public class InicializarDALListener implements ServletContextListener, HttpSessionListener {

	public InicializarDALListener() {

	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent sce) {

		// Inicializar DAL y Tablas
		ServletContext application = sce.getServletContext();

		UsuarioDAO usuarioDAO;

		usuarioDAO = DALFactory.getUsuariosDAL();

		// usuarioDAO.alta(new Usuario("usuario1", "pass1", Nivel.ADMIN));
		// usuarioDAO.alta(new Usuario("usuario2", "pass2", Nivel.USUARIO));

		application.setAttribute("usuariosdal", usuarioDAO);

		ProductosDAL productosDal;

		productosDal = DALFactory.getProductosDAL();

		// productosDal.nuevo(new Producto(1, "producto1", "descripcion1", 10.00));
		// productosDal.nuevo(new Producto(2, "producto2", "descripcion2", 20.00));

		application.setAttribute("productosdal", productosDal);

		FacturaDAO facturaDAO = DALFactory.getFacturasDAO();
		application.setAttribute("facturasdal", facturaDAO);

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Carrito carrito = new Carrito();
		HttpSession session = se.getSession();
		session.setAttribute("carrito", carrito);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

	}
}
