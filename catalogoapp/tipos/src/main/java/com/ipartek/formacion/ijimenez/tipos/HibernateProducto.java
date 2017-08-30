package com.ipartek.formacion.ijimenez.tipos;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity(name = "PRODUCTOS")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "PRODUCTO_SEQ", sequenceName = "producto_sequence")
public class HibernateProducto extends Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTO_SEQ")
	@Column(name = "ID_PRODUCTO")
	int id;

	@Column(name = "NOMBRE", unique = true)
	String nombre;

	@Column(name = "DESCRIPCION")
	@Lob
	String descripcion;

	@Column(name = "PRECIO")
	double precio;

	@OneToMany(mappedBy = "producto")
	private Collection<HibernateFacturaLinea> facturaLinea = new ArrayList<HibernateFacturaLinea>();

	@Transient
	String idErrores;
	@Transient
	String nombreErrores;
	@Transient
	String descripcionErrores;
	@Transient
	String precioErrores;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Collection<HibernateFacturaLinea> getFacturaLinea() {
		return facturaLinea;
	}

	public void setFacturaLinea(Collection<HibernateFacturaLinea> facturaLinea) {
		this.facturaLinea = facturaLinea;
	}

	public String getIdErrores() {
		return idErrores;
	}

	public void setIdErrores(String idErrores) {
		this.idErrores = idErrores;
	}

	public String getNombreErrores() {
		return nombreErrores;
	}

	public void setNombreErrores(String nombreErrores) {
		this.nombreErrores = nombreErrores;
	}

	public String getDescripcionErrores() {
		return descripcionErrores;
	}

	public void setDescripcionErrores(String descripcionErrores) {
		this.descripcionErrores = descripcionErrores;
	}

	public String getPrecioErrores() {
		return precioErrores;
	}

	public void setPrecioErrores(String precioErrores) {
		this.precioErrores = precioErrores;
	}

	public HibernateProducto() {

	}

	public HibernateProducto(int id, String nombre, String descripcion, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public HibernateProducto(Producto producto) {
		super(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());
	}
}
