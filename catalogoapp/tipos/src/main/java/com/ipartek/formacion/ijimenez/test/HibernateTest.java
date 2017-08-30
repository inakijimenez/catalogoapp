package com.ipartek.formacion.ijimenez.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ipartek.formacion.ijimenez.tipos.HibernateFactura;
import com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.HibernateProducto;
import com.ipartek.formacion.ijimenez.tipos.HibernateRol;
import com.ipartek.formacion.ijimenez.tipos.HibernateUsuario;

public class HibernateTest {

	private static EntityManager manager;

	private static EntityManagerFactory emf;

	public static void main(String[] args) {

		HibernateUsuario usuario = new HibernateUsuario();
		usuario.setUsername("user1");
		usuario.setPassword("pass1");
		usuario.setNombre_completo("name1");

		HibernateUsuario usuario2 = new HibernateUsuario();
		usuario2.setUsername("user2");
		usuario2.setPassword("pass2");
		usuario2.setNombre_completo("name2");

		HibernateRol rol = new HibernateRol();
		rol.setNombreRol("Admin");
		rol.setDescripcionRol("Administrador de la aplicacion");
		rol.getUsuario().add(usuario);
		rol.getUsuario().add(usuario2);

		usuario.setRol(rol);
		usuario2.setRol(rol);

		HibernateFactura factura = new HibernateFactura();
		factura.setUsuario(usuario2);
		factura.setFecha(new Date());
		factura.setNúmero_factura("001");

		HibernateFactura factura2 = new HibernateFactura();
		factura2.setUsuario(usuario2);
		factura2.setFecha(new Date());
		factura2.setNúmero_factura("002");

		HibernateProducto producto = new HibernateProducto();
		producto.setNombre("producto1");
		producto.setDescripcion("descripcion1");
		producto.setPrecio(10.00);

		HibernateProducto producto2 = new HibernateProducto();
		producto2.setNombre("producto2");
		producto2.setDescripcion("descripcion2");
		producto2.setPrecio(20.00);

		HibernateFacturaLinea facturaLinea = new HibernateFacturaLinea(producto2, factura2, 23, 20.00);
		HibernateFacturaLinea facturaLinea2 = new HibernateFacturaLinea(producto, factura2, 45, 10.00);

		emf = Persistence.createEntityManagerFactory("aplicacion");
		manager = emf.createEntityManager();

		manager.getTransaction().begin();

		manager.persist(usuario);
		manager.persist(usuario2);
		manager.persist(rol);
		manager.persist(factura);
		manager.persist(factura2);
		manager.persist(producto);
		manager.persist(producto2);
		manager.persist(facturaLinea);
		manager.persist(facturaLinea2);

		manager.getTransaction().commit();

		emf.close();

	}
}
