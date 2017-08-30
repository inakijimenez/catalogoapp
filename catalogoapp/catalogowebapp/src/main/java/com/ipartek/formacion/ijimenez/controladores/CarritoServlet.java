package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.ijimenez.dal.ProductosDALHibernate;
import com.ipartek.formacion.ijimenez.tipos.Carrito;
import com.ipartek.formacion.ijimenez.tipos.CarritoLinea;
import com.ipartek.formacion.ijimenez.tipos.Producto;

/**
 * Servlet implementation class Carrito
 */
@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductosDALHibernate productoDAL = null;

		try {
			ServletContext application = getServletContext();
			HttpSession session = request.getSession();
			productoDAL = (ProductosDALHibernate) application.getAttribute("productosdal");
			Carrito carrito = (Carrito) session.getAttribute("carrito");
			Collection<CarritoLinea> lineas = carrito.getLineas();
			request.setAttribute("lineas", lineas);
			// productoDAL.abrir();

			// System.out.println(carrito);

			List<Producto> productos = productoDAL.findAll();

			request.setAttribute("productos", productos);

			if (request.getParameter("opeliminar") != null) {
				String nombre = request.getParameter("nombre");
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));

				for (CarritoLinea cl : lineas) {
					// System.out.println(cl.getProducto());
					// System.out.println("el nombre es " + nombre);
					// System.out.println(productoDAL.findByName(nombre));
					if (cl.getProducto().getNombre().equals(nombre)) {

						cl.setCantidad(cl.getCantidad() - cantidad);

						if (cl.getCantidad() <= 0) {
							lineas.remove(cl);
							break;
						}

					}
				}

			}

			if (request.getParameter("opeliminartodos") != null) {
				String nombre = request.getParameter("nombre");

				for (CarritoLinea cl : lineas) {
					if (cl.getProducto().getNombre().equals(nombre)) {
						lineas.remove(cl);
						break;
					}

				}
			}

			application.setAttribute("carrito", carrito);

			request.getRequestDispatcher(Rutas.RUTA_CARRITO).forward(request, response);

		} catch (Exception e) {
			System.out.println("HA CASCADO EL CARRITO");
			e.printStackTrace();
		}
		// finally {
		// productoDAL.cerrar();
		// }

	}

}
