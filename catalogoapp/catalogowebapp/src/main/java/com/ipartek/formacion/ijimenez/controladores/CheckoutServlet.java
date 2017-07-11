package com.ipartek.formacion.ijimenez.controladores;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.ijimenez.tipos.Carrito;
import com.ipartek.formacion.ijimenez.tipos.CarritoLinea;
import com.ipartek.formacion.ijimenez.tipos.Factura;
import com.ipartek.formacion.ijimenez.tipos.FacturaLinea;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		Collection<CarritoLinea> lineas = carrito.getLineas();
		request.setAttribute("lineas", lineas);

		Factura factura = new Factura();
		Collection<FacturaLinea> facturaLineas;

		for (CarritoLinea cl : lineas) {

		}

		request.getRequestDispatcher(Rutas.RUTA_CHECKOUT).forward(request, response);

	}

}
