package com.ipartek.formacion.ijimenez.dal;

import com.ipartek.formacion.ijimenez.tipos.Producto;

public interface ProductosDAL extends IpartekDAO {

	public void insert(Producto producto);

	public void update(Producto producto);

	public void delete(Producto producto);

	public Producto findById(int id);

	public Producto[] findAll();

	public Producto findByName(String name);

	public boolean productoExiste(Producto producto);

}
