package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ijimenez.dal.ProductosDAL;
import com.ipartek.formacion.ijimenez.tipos.Producto;

@WebServlet("/administracion")
public class ProductosCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		ProductosDAL dal = (ProductosDAL) application.getAttribute("productosdal");

		String op = request.getParameter("op");

		// if (dal == null) {
		// dal = DALFactory.getProductosDAL();
		//
		// dal.nuevo(new Producto(1, "producto1", "descripcion1", 10.00));
		// dal.nuevo(new Producto(2, "producto2", "descripcion2", 20.00));
		//
		// application.setAttribute("productosdal", dal);
		// }

		if (op == null) {

			Producto[] productos = dal.buscarTodosLosProductos();

			request.setAttribute("productos", productos);

			request.getRequestDispatcher(Rutas.RUTA_ADMINISTRACION).forward(request, response);

		}

		else {

			Producto producto;

			int id;

			switch (op) {
			case "nuevo":
				request.getRequestDispatcher(Rutas.RUTA_FORMULARIO).forward(request, response);
				break;

			case "modificar":
				id = Integer.parseInt(request.getParameter("id"));
				producto = dal.buscarPorId(id);
				request.setAttribute("producto", producto);
				request.getRequestDispatcher(Rutas.RUTA_FORMULARIO).forward(request, response);
				break;

			case "borrar":
				id = Integer.parseInt(request.getParameter("id"));
				producto = dal.buscarPorId(id);
				dal.borrar(producto);
				response.sendRedirect(Rutas.RUTA_SERVLET_LISTADO);

				break;
			}
		}
	}

}
