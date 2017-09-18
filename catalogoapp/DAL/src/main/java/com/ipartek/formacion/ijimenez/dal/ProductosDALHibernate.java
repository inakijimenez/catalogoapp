package com.ipartek.formacion.ijimenez.dal;

import java.util.List;

import javax.persistence.EntityManager;

import com.ipartek.formacion.ijimenez.tipos.HibernateProducto;
import com.ipartek.formacion.ijimenez.tipos.Producto;

public class ProductosDALHibernate extends IpartekDAOHibernate implements ProductosDAL {

	private static IpartekDAOHibernate idh = new IpartekDAOHibernate();
	private static EntityManager manager = idh.crearManager();

	@Override
	public void insert(Producto producto) {

		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(producto);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al insertar producto", e);
		}

		// emf.close();
	}

	@Override
	public void update(Producto producto) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(producto);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al actualizar producto", e);
		}

		// emf.close();

	}

	@Override
	public void delete(Producto producto) {

		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(producto));
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al borrar producto", e);
		}

		// emf.close();
	}

	@Override
	public Producto findById(int id) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		// System.out.println(manager.find(HibernateProducto.class, id));
		Producto producto;
		try {
			manager.getTransaction().begin();
			producto = manager.find(HibernateProducto.class, id);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar producto por id", e);
		}

		// emf.close();

		return producto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findAll() {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		List<Producto> productos;
		try {
			manager.getTransaction().begin();
			productos = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateProducto").getResultList();
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar todos los productos", e);
		}

		// emf.close();
		// for (Producto p : productos) {
		// System.out.println(p + " lista");
		// }

		return productos;
	}

	@Override
	public Producto findByName(String nombre) {

		Producto producto;

		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			// String select = ("from com.ipartek.formacion.ijimenez.tipos.HibernateUsuario where username = " +
			// username);
			producto = (Producto) manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateProducto where nombre = '" + nombre + "'")
					.getSingleResult();
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar producto por nombre", e);
		}

		// emf.close();

		return producto;
	}

	@Override
	public boolean productoExiste(Producto producto) {

		return (findById(producto.getId()) != null);
	}

}
