package com.ipartek.formacion.ijimenez.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ipartek.formacion.ijimenez.tipos.HibernateProducto;
import com.ipartek.formacion.ijimenez.tipos.Producto;

public class ProductosDALHibernate extends IpartekDAOHibernate implements ProductosDAL {

	@Override
	public void insert(Producto producto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(producto);
		manager.getTransaction().commit();
		emf.close();
	}

	@Override
	public void update(Producto producto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(producto);
		manager.getTransaction().commit();
		emf.close();

	}

	@Override
	public void delete(Producto producto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(producto));
		manager.getTransaction().commit();
		emf.close();
	}

	@Override
	public Producto findById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		// System.out.println(manager.find(HibernateProducto.class, id));
		Producto producto = manager.find(HibernateProducto.class, id);
		manager.getTransaction().commit();
		emf.close();
		return producto;
	}

	@Override
	public List<Producto> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Producto> productos = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateProducto").getResultList();
		manager.getTransaction().commit();
		emf.close();
		// for (Producto p : productos) {
		// System.out.println(p + " lista");
		// }

		return productos;
	}

	@Override
	public Producto findByName(String nombre) {

		Producto producto;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		// String select = ("from com.ipartek.formacion.ijimenez.tipos.HibernateUsuario where username = " + username);
		producto = (Producto) manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateProducto where nombre = '" + nombre + "'").getSingleResult();
		manager.getTransaction().commit();
		emf.close();

		return producto;
	}

	@Override
	public boolean productoExiste(Producto producto) {

		return (findById(producto.getId()) != null);
	}

}
