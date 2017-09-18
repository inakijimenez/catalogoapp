package com.ipartek.formacion.ijimenez.dal;

import java.util.List;

import javax.persistence.EntityManager;

import com.ipartek.formacion.ijimenez.tipos.FacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea;

public class FacturaLineaDAOHibernate extends IpartekDAOHibernate {

	private static IpartekDAOHibernate idh = new IpartekDAOHibernate();
	private static EntityManager manager = idh.crearManager();

	public void insert(FacturaLinea fl) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(fl);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al insertar linea", e);
		}

		// emf.close();

	}

	public void update(FacturaLinea fl) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(fl);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al actualizar linea", e);
		}

		// emf.close();

	}

	public void delete(FacturaLinea fl) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(fl));
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al borrar linea", e);
		}

		// emf.close();
	}

	public void delete(int id) {
		FacturaLinea fl = new HibernateFacturaLinea();
		fl = findById(id);

		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(fl));
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al borrar linea", e);
		}

		// emf.close();
	}

	@SuppressWarnings("unchecked")
	public List<FacturaLinea> findByProductoId(int id) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		List<FacturaLinea> fls;
		try {
			manager.getTransaction().begin();
			fls = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea where producto.id = '" + id + "'").getResultList();
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar linea por id de producto", e);
		}

		// emf.close();
		return fls;
	}

	@SuppressWarnings("unchecked")
	public List<FacturaLinea> findByFacturaId(int id) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		List<FacturaLinea> fls;
		try {
			manager.getTransaction().begin();
			fls = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea where factura.id = '" + id + "'").getResultList();
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar linea por id de factura", e);
		}

		// emf.close();
		return fls;
	}

	public FacturaLinea findById(int id) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		FacturaLinea fl;
		try {
			manager.getTransaction().begin();
			fl = manager.find(HibernateFacturaLinea.class, id);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar linea por id", e);
		}

		// emf.close();

		return fl;
	}

	public FacturaLinea findById(int idFactura, int idProducto) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		FacturaLinea fl;
		try {
			manager.getTransaction().begin();
			fl = (FacturaLinea) manager.createQuery(
					"from com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea where producto.id = '" + idProducto + "' and factura.id= '" + idFactura
							+ "'").getSingleResult();

			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar linea por id de factura y producto", e);
		}

		// emf.close();

		return fl;
	}

	@SuppressWarnings("unchecked")
	public List<FacturaLinea> findAll() {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		List<FacturaLinea> fls;
		try {
			manager.getTransaction().begin();
			fls = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea").getResultList();
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar todas las lineas", e);
		}

		// emf.close();

		return fls;
	}

}
