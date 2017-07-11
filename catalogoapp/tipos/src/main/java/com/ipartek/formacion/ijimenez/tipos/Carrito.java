package com.ipartek.formacion.ijimenez.tipos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Carrito implements Compra {

	private int idUsuario;

	private List<CarritoLinea> lineas = new ArrayList<CarritoLinea>();

	public Carrito(int idUsuario, List<CarritoLinea> lineas) {
		super();
		this.idUsuario = idUsuario;
		this.lineas = lineas;
	}

	public Carrito() {

	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void addProductoYCantidad(Producto producto, int cantidad) {
		lineas.add(new CarritoLinea(producto, cantidad));
	}

	public Collection<CarritoLinea> getLineas() {
		return lineas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsuario;
		result = prime * result + ((lineas == null) ? 0 : lineas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrito other = (Carrito) obj;
		if (idUsuario != other.idUsuario)
			return false;
		if (lineas == null) {
			if (other.lineas != null)
				return false;
		} else if (!lineas.equals(other.lineas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carrito [idUsuario=" + idUsuario + ", lineas=" + lineas + "]";
	}

}
