package com.ipartek.formacion.ijimenez.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ipartek.formacion.ijimenez.tipos.FacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea;

public class FacturaLineaDAOHibernate extends IpartekDAOHibernate {

	public void insert(FacturaLinea fl) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(fl);
		manager.getTransaction().commit();
		emf.close();

	}

	public void update(FacturaLinea fl) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(fl);
		manager.getTransaction().commit();
		emf.close();

	}

	public void delete(FacturaLinea fl) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(fl));
		manager.getTransaction().commit();
		emf.close();
	}

	public void delete(int id) {
		FacturaLinea fl = new HibernateFacturaLinea();
		fl = findById(id);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(fl));
		manager.getTransaction().commit();
		emf.close();
	}

	public List<FacturaLinea> findByProductoId(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<FacturaLinea> fls = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea where producto.id = '" + id + "'").getResultList();
		manager.getTransaction().commit();
		emf.close();
		return fls;
	}

	public List<FacturaLinea> findByFacturaId(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<FacturaLinea> fls = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea where factura.id = '" + id + "'").getResultList();
		manager.getTransaction().commit();
		emf.close();
		return fls;
	}

	public FacturaLinea findById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		FacturaLinea fl = manager.find(HibernateFacturaLinea.class, id);
		manager.getTransaction().commit();
		emf.close();
		return fl;
	}

	public FacturaLinea findById(int idFactura, int idProducto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		FacturaLinea fl = (FacturaLinea) manager.createQuery(
				"from com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea where producto.id = '" + idProducto + "' and factura.id= '" + idFactura + "'").getSingleResult();

		manager.getTransaction().commit();
		emf.close();
		return fl;
	}

	public List<FacturaLinea> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<FacturaLinea> fls = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateFacturaLinea").getResultList();
		manager.getTransaction().commit();
		emf.close();
		return fls;
	}

}
