package com.ipartek.formacion.ijimenez.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IpartekDAOHibernate implements IpartekDAO {

	private static EntityManager manager;

	private static EntityManagerFactory emf;

	public EntityManager crearManager() {

		if (emf == null)
			emf = Persistence.createEntityManagerFactory("aplicacion");

		manager = emf.createEntityManager();
		return manager;
	}

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

	}

	@Override
	public void reutilizarConexion(IpartekDAO dao) {

	}

	@Override
	public void iniciarTransaccion() {

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
