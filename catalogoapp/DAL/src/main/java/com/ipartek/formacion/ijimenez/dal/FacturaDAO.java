package com.ipartek.formacion.ijimenez.dal;

import com.ipartek.formacion.ijimenez.tipos.Factura;
import com.ipartek.formacion.ijimenez.tipos.FacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.Producto;

public interface FacturaDAO extends IpartekDAO {
	public Factura[] findAll();

	public Factura findById(int id);

	public Factura findByIdFacturaCompleta(int id);

	public int insert(Factura factura);

	public void update(Factura factura);

	public void delete(Factura factura);

	public void delete(int id);

	public void insertLinea(FacturaLinea linea);

	public void deleteLinea(Producto producto);

	public void updateLinea(FacturaLinea linea);

	public void findLineaByProductoId(int idFactura, int idProducto);

	public FacturaLinea[] findAllLineas(int idFactura);
}
