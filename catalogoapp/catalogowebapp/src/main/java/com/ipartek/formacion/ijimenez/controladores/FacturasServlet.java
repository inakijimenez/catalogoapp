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

import com.ipartek.formacion.ijimenez.dal.FacturaDAOHibernate;
import com.ipartek.formacion.ijimenez.dal.FacturaLineaDAOHibernate;
import com.ipartek.formacion.ijimenez.tipos.Factura;
import com.ipartek.formacion.ijimenez.tipos.FacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.HibernateFactura;

@WebServlet("/facturas")
public class FacturasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/facturacrud.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/facturas.jsp";
	static final String RUTA_SERVLET_LISTADO = "/facturas";

	private static Logger log = Logger.getLogger(UsuarioCRUDServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FacturaDAOHibernate facturaDAO = null;
		FacturaLineaDAOHibernate flDAO = null;

		try {
			ServletContext application = request.getServletContext();
			facturaDAO = (FacturaDAOHibernate) application.getAttribute("facturasdal");
			flDAO = (FacturaLineaDAOHibernate) application.getAttribute("fldao");

			String op = request.getParameter("op");

			if (op == null) {

				List<Factura> facturas = facturaDAO.findAll();

				System.out.println(facturas);

				request.setAttribute("facturas", facturas);

				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
				return;
			}

			else {

				HibernateFactura factura;
				System.out.println(request);
				// usuario = (HibernateUsuario) usuarioDAO.findByUsername(request.getParameter("id"));
				// System.out.println(usuario);

				switch (op) {
				case "borrar":
					// usuario = usuarioDAO.findById(id);
					log.info("Se va a llevar a cabo la operacion de borrar una factura");
					factura = (HibernateFactura) facturaDAO.findById(Integer.parseInt(request.getParameter("id")));

					facturaDAO.delete(factura);

					response.sendRedirect(RUTA_SERVLET_LISTADO);
					break;
				case "modificar":

					factura = (HibernateFactura) facturaDAO.findById(Integer.parseInt(request.getParameter("id")));
					request.setAttribute("factura", factura);

					List<FacturaLinea> lineas = flDAO.findByFacturaId(factura.getId());
					request.setAttribute("lineas", lineas);

					request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
					break;
				default:
					request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
				}
			}
		} catch (Exception e) {
			System.out.println("HA CASCADO FACTURAS");
			e.printStackTrace();
		}
		// finally {
		// usuarioDAO.cerrar();
		// }
	}

}
