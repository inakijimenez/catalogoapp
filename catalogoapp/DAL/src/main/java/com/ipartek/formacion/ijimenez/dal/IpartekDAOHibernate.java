package com.ipartek.formacion.ijimenez.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IpartekDAOHibernate implements IpartekDAO {

	public static EntityManager manager;

	private static EntityManagerFactory emf;

	public EntityManager crearManager() {
		emf = Persistence.createEntityManagerFactory("aplicacion");
		manager = emf.createEntityManager();
		return manager;
	}

	@Override
	public void abrir() {
		try {
			manager = crearManager();
			manager.getTransaction().begin();
		} catch (DAOException e) {
			throw new DAOException("Error al abrir la conexion", e);
		}
	}

	@Override
	public void cerrar() {
		emf.close();
	}

	@Override
	public void reutilizarConexion(IpartekDAO dao) {

	}

	@Override
	public void iniciarTransaccion() {
		try {
			manager = crearManager();
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al iniciar la transaccion", e);
		}

	}

	@Override
	public void confirmarTransaccion() {

	}

	@Override
	public void deshacerTransaccion() {
		try {
			manager = crearManager();
			manager.getTransaction().rollback();
		} catch (DAOException e) {
			throw new DAOException("Error al deshacer transacción", e);
		}
	}

}
