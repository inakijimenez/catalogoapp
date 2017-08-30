package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.ijimenez.dal.FacturaDAOHibernate;
import com.ipartek.formacion.ijimenez.dal.FacturaLineaDAOHibernate;
import com.ipartek.formacion.ijimenez.tipos.Carrito;
import com.ipartek.formacion.ijimenez.tipos.CarritoLinea;
import com.ipartek.formacion.ijimenez.tipos.FacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.HibernateFactura;
import com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.HibernateProducto;
import com.ipartek.formacion.ijimenez.tipos.HibernateUsuario;
import com.ipartek.formacion.ijimenez.tipos.Usuario;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FacturaDAOHibernate facturaDAO = null;
		FacturaLineaDAOHibernate flDAO = null;

		ServletContext application = getServletContext();
		HttpSession session = request.getSession();
		facturaDAO = (FacturaDAOHibernate) application.getAttribute("facturasdal");
		flDAO = (FacturaLineaDAOHibernate) application.getAttribute("fldao");

		Carrito carrito = (Carrito) session.getAttribute("carrito");
		Collection<CarritoLinea> lineas = carrito.getLineas();
		request.setAttribute("lineas", lineas);

		Usuario usuario = new HibernateUsuario();

		usuario = (Usuario) session.getAttribute("usuario");
		System.out.println(usuario);
		// Factura factura = new HibernateFactura((HibernateUsuario) usuarioDAO.findById(carrito.getIdUsuario()), new Date());

		HibernateFactura factura = new HibernateFactura();

		Date date = new Date();
		factura.setFecha(date);
		factura.setNúmero_factura("0005");
		factura.setUsuario((HibernateUsuario) usuario);
		facturaDAO.insert(factura);

		request.setAttribute("factura", factura);

		for (CarritoLinea cl : lineas) {

			FacturaLinea fl = new HibernateFacturaLinea((HibernateProducto) cl.getProducto(), (HibernateFactura) factura, cl.getCantidad(), cl.getProducto().getPrecio());
			flDAO.insert(fl);

		}

		request.getRequestDispatcher(Rutas.RUTA_CHECKOUT).forward(request, response);

	}

}
