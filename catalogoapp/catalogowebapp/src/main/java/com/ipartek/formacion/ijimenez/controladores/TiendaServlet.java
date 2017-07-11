package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.ijimenez.dal.ProductosDALColeccion;
import com.ipartek.formacion.ijimenez.tipos.Carrito;
import com.ipartek.formacion.ijimenez.tipos.CarritoLinea;
import com.ipartek.formacion.ijimenez.tipos.Producto;

/**
 * Servlet implementation class TiendaServlet
 */
@WebServlet("/tienda")
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductosDALColeccion productoDAL = null;

		try {
			ServletContext application = getServletContext();
			HttpSession session = request.getSession();
			productoDAL = (ProductosDALColeccion) application.getAttribute("productosdal");
			Carrito carrito = (Carrito) session.getAttribute("carrito");
			Collection<CarritoLinea> lineas = carrito.getLineas();

			productoDAL.abrir();

			Producto[] productos = productoDAL.findAll();

			request.setAttribute("productos", productos);

			String op = request.getParameter("op");
			boolean existe = false;

			if (op != null && op.equals("sumar")) {
				int id = Integer.parseInt(request.getParameter("id"));
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));

				for (CarritoLinea cl : lineas) {
					if (cl.getProducto().getNombre().equals(productoDAL.findById(id).getNombre())) {
						existe = true;
						cl.setCantidad(cantidad + cl.getCantidad());

					}
				}
				if (!existe) {
					carrito.addProductoYCantidad(productoDAL.findById(id), cantidad);
				}
			}

			session.setAttribute("carrito", carrito);
			// System.out.println("Se ha añadido al carrito " + carrito);

			request.getRequestDispatcher(Rutas.RUTA_TIENDA).forward(request, response);

		} catch (Exception e) {
			System.out.println("HA CASCADO LA TIENDA");
			e.printStackTrace();
		} finally {
			productoDAL.cerrar();
		}

	}
}
