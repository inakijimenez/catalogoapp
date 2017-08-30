package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ijimenez.dal.ProductosDALHibernate;
import com.ipartek.formacion.ijimenez.tipos.HibernateProducto;
import com.ipartek.formacion.ijimenez.tipos.Producto;

@WebServlet("/administracion")
public class ProductosCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductosDALHibernate productoDAL = null;

		try {
			ServletContext application = getServletContext();
			productoDAL = (ProductosDALHibernate) application.getAttribute("productosdal");
			// productoDAL.abrir();

			String op = request.getParameter("op");

			if (op == null) {

				List<Producto> productos = productoDAL.findAll();

				request.setAttribute("productos", productos);

				request.getRequestDispatcher(Rutas.RUTA_ADMINISTRACION).forward(request, response);

			}

			else {

				HibernateProducto producto;

				int id;

				switch (op) {
				case "nuevo":
					request.getRequestDispatcher(Rutas.RUTA_FORMULARIO).forward(request, response);
					break;

				case "modificar":
					id = Integer.parseInt(request.getParameter("id"));
					producto = (HibernateProducto) productoDAL.findById(id);
					request.setAttribute("producto", producto);
					request.getRequestDispatcher(Rutas.RUTA_FORMULARIO).forward(request, response);
					break;

				case "borrar":
					id = Integer.parseInt(request.getParameter("id"));
					producto = (HibernateProducto) productoDAL.findById(id);
					productoDAL.delete(producto);
					response.sendRedirect(Rutas.RUTA_SERVLET_LISTADO);

					break;
				}
			}
		} catch (Exception e) {
			System.out.println("HA CASCADO PRODUCTOCRUD");
			e.printStackTrace();
		}
		// finally {
		// productoDAL.cerrar();
		// }

	}

}
