package com.ipartek.formacion.ijimenez.dal;

import com.ipartek.formacion.ijimenez.tipos.Producto;

public interface ProductosDAL {

	public void nuevo(Producto producto);

	public void modificar(Producto producto);

	public void borrar(Producto producto);

	public Producto buscarPorId(int id);

	public Producto[] buscarTodosLosProductos();

	public boolean productoExiste(Producto producto);

}
