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

import com.ipartek.formacion.ijimenez.dal.ProductosDALHibernate;
import com.ipartek.formacion.ijimenez.tipos.HibernateProducto;

/**
 * Servlet implementation class ProductosFormServlet
 */
@WebServlet("/productosform")
public class ProductosFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ProductosFormServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductosDALHibernate productoDAL = null;

		String op = request.getParameter("op");

		int id = 0;
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		double precio;

		try {

			ServletContext application = request.getServletContext();
			productoDAL = (ProductosDALHibernate) application.getAttribute("productosdal");
			// productoDAL.abrir();

			if (request.getParameter("precio") == null || request.getParameter("precio").equals("")) {
				precio = 0;

			} else {
				precio = Double.parseDouble(request.getParameter("precio"));
			}

			RequestDispatcher rutalistado = request.getRequestDispatcher(Rutas.RUTA_SERVLET_LISTADO);
			RequestDispatcher rutaFormulario = request.getRequestDispatcher(Rutas.RUTA_FORMULARIO);

			if (op == null) {
				rutalistado.forward(request, response);
				return;
			}

			HibernateProducto producto = new HibernateProducto(id, nombre, descripcion, precio);

			// boolean idValido = producto.getId() != 0;
			boolean nombreValido = producto.getNombre() != null && producto.getNombre().length() != 0;
			boolean descripcionValido = producto.getDescripcion() != null && producto.getDescripcion().length() != 0;
			boolean precioValido = producto.getPrecio() != 0;

			switch (op) {
			case "nuevo":
				log.info("Se va a llevar a cabo la operacion de alta un producto");
				if (!productoDAL.productoExiste(producto)) {

					if (nombreValido && descripcionValido && precioValido) {

						productoDAL.insert(producto);
						response.sendRedirect(Rutas.RUTA_SERVLET_LISTADO);
						break;
					}

					// if (!idValido) {
					// producto.setIdErrores("El id debe ser un numero y distinto de 0");
					// }
					if (!nombreValido) {
						producto.setNombreErrores("El nombre debe contener al menos un caracter");
					}
					if (!descripcionValido) {
						producto.setDescripcionErrores("La descripcion debe contener al menos un caracter");
					}
					if (!precioValido) {
						producto.setPrecioErrores("El precio debe ser un numero y distinto de 0");
					}

					request.setAttribute("producto", producto);
					rutaFormulario.forward(request, response);

				}
				break;
			case "modificar":

				log.info("Se va a llevar a cabo la operacion de modificar un producto");

				producto.setId(productoDAL.findByName(producto.getNombre()).getId());
				// if (productoDAL.productoExiste(producto)) {

				if (nombreValido && descripcionValido && precioValido) {

					System.out.println(producto);
					productoDAL.update(producto);
					response.sendRedirect(Rutas.RUTA_SERVLET_LISTADO);
					break;
				}

				if (!nombreValido) {
					producto.setNombreErrores("El nombre debe contener al menos un caracter");
				}
				if (!descripcionValido) {
					producto.setDescripcionErrores("La descripcion debe contener al menos un caracter");
				}
				if (!precioValido) {
					producto.setPrecioErrores("El precio debe ser un numero distinto de 0");
				}

				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);

				// }
				break;

			}
		} catch (Exception e) {
			System.out.println("HA CASCADO PRODUCTOFORM");
			e.printStackTrace();
		}
		// finally {
		// productoDAL.cerrar();
		// }
	}
}
