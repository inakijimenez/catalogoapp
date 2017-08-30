package com.ipartek.formacion.ijimenez.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ipartek.formacion.ijimenez.tipos.Factura;
import com.ipartek.formacion.ijimenez.tipos.FacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.HibernateFactura;
import com.ipartek.formacion.ijimenez.tipos.Producto;

public class FacturaDAOHibernate extends IpartekDAOHibernate implements FacturaDAO {

	@Override
	public List<Factura> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Factura> facturas = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateFactura").getResultList();
		manager.getTransaction().commit();
		emf.close();
		return facturas;
	}

	@Override
	public Factura findById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		// System.out.println(manager.find(HibernateProducto.class, id));
		Factura factura = manager.find(HibernateFactura.class, id);
		manager.getTransaction().commit();
		emf.close();
		return factura;
	}

	@Override
	public Factura findByIdFacturaCompleta(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Factura factura) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(factura);
		manager.getTransaction().commit();
		emf.close();
		return 0;
	}

	@Override
	public void update(Factura factura) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(factura);
		manager.getTransaction().commit();
		emf.close();

	}

	@Override
	public void delete(Factura factura) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(factura));
		manager.getTransaction().commit();
		emf.close();
	}

	@Override
	public void delete(int id) {

		Factura factura = new HibernateFactura();
		factura = findById(id);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(factura));
		manager.getTransaction().commit();
		emf.close();
	}

	@Override
	public void insertLinea(FacturaLinea linea) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLinea(FacturaLinea linea) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findLineaByProductoId(int idFactura, int idProducto) {
		// TODO Auto-generated method stub

	}

	@Override
	public FacturaLinea[] findAllLineas(int idFactura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLinea(Producto producto) {
		// TODO Auto-generated method stub

	}

}
