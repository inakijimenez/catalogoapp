package com.ipartek.formacion.ijimenez.dal;

import java.util.Map;
import java.util.TreeMap;

import com.ipartek.formacion.ijimenez.tipos.Producto;

public class ProductosDALColeccion implements ProductosDAL {

	private Map<Integer, Producto> productos = new TreeMap<Integer, Producto>();

	public void nuevo(Producto producto) {
		productos.put(producto.getId(), producto);

	}

	public void modificar(Producto producto) {
		productos.put(producto.getId(), producto);

	}

	public void borrar(Producto producto) {
		productos.remove(producto.getId());

	}

	public Producto buscarPorId(int id) {

		return productos.get(id);
	}

	public Producto[] buscarTodosLosProductos() {

		return productos.values().toArray(new Producto[productos.size()]);
	}

	public boolean productoExiste(Producto producto) {

		return productos.containsKey(producto.getId());
	}

}
