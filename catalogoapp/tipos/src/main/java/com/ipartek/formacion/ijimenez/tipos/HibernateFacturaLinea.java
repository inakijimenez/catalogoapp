package com.ipartek.formacion.ijimenez.tipos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "FACTURAS_PRODUCTOS")
public class HibernateFacturaLinea extends FacturaLinea {

	@Id
	@GeneratedValue
	int idLinea;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PRODUCTO")
	HibernateProducto producto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FACTURA")
	HibernateFactura factura;

	int cantidad;
	double precio;

	public int getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}

	public HibernateProducto getProducto() {
		return producto;
	}

	public void setProducto(HibernateProducto producto) {
		this.producto = producto;
	}

	public HibernateFactura getFactura() {
		return factura;
	}

	public void setFactura(HibernateFactura factura) {
		this.factura = factura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public HibernateFacturaLinea(HibernateProducto producto, HibernateFactura factura, int cantidad, double precio) {
		super();
		this.producto = producto;
		this.factura = factura;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public HibernateFacturaLinea() {
		super();
	}

}
