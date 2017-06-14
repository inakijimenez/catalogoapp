package com.ipartek.formacion.ijimenez.tipos;

public class Producto {

	int id;
	String nombre, descripcion;
	double precio;
	String idErrores, nombreErrores, descripcionErrores, precioErrores;

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

	public Producto() {

	}

	public Producto(int id, String nombre, String descripcion, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}

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

}
