package com.ipartek.formacion.ijimenez.dal;

import java.util.List;

import com.ipartek.formacion.ijimenez.tipos.Producto;

public interface ProductosDAL extends IpartekDAO {

	public void insert(Producto producto);

	public void update(Producto producto);

	public void delete(Producto producto);

	public Producto findById(int id);

	public List<Producto> findAll();

	public Producto findByName(String name);

	public boolean productoExiste(Producto producto);

}
