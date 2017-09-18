package com.ipartek.formacion.ijimenez.dal;

import java.util.List;

import javax.persistence.EntityManager;

import com.ipartek.formacion.ijimenez.tipos.HibernateUsuario;
import com.ipartek.formacion.ijimenez.tipos.Usuario;

public class UsuarioDAOHibernate extends IpartekDAOHibernate implements UsuarioDAO {

	private static IpartekDAOHibernate idh = new IpartekDAOHibernate();
	private static EntityManager manager = idh.crearManager();

	public UsuarioDAOHibernate() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll() {

		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		List<Usuario> usuarios;
		try {
			manager.getTransaction().begin();
			usuarios = manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateUsuario").getResultList();
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar todos los usuarios", e);
		}

		// emf.close();
		// for (Usuario u : usuarios) {
		// System.out.println(u + "lista");
		// }

		return usuarios;

	}

	@Override
	public Usuario findById(int id) {

		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		// System.out.println(manager.find(HibernateUsuario.class, id));
		Usuario usuario;
		try {
			manager.getTransaction().begin();
			usuario = manager.find(HibernateUsuario.class, id);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar usuario por id", e);
		}

		// emf.close();
		return usuario;

	}

	public Usuario findByUsername(String username) {

		// System.out.println("ESTAMOS EN FINDBYUSERNAME \n \n \n");

		Usuario usuario;

		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();
		try {
			manager.getTransaction().begin();
			// String select = ("from com.ipartek.formacion.ijimenez.tipos.HibernateUsuario where username = " +
			// username);
			usuario = (Usuario) manager.createQuery("from com.ipartek.formacion.ijimenez.tipos.HibernateUsuario where username = '" + username + "'")
					.getSingleResult();
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al buscar usuario por nombre", e);
		}
		// emf.close();

		// System.out.println(" FINDBYUSERNAME " + usuario);
		return usuario;
	}

	@Override
	public int insert(Usuario usuario) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();

		// EntityManager manager = idh.crearManager();
		try {
			manager.getTransaction().begin();

			manager.persist(usuario);

			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al insertar usuario", e);
		}

		// emf.close();
		return 0;
	}

	@Override
	public void update(Usuario usuario) {
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(usuario);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al actualizar usuario", e);
		}
		// emf.close();

	}

	@Override
	public void delete(Usuario usuario) {

		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(usuario));
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al borrar usuario", e);
		}
		// emf.close();

	}

	@Override
	public void delete(int id) {
		HibernateUsuario usuario = (HibernateUsuario) findById(id);
		// EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		// EntityManager manager = emf.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.remove(usuario);
			manager.getTransaction().commit();
		} catch (DAOException e) {
			manager.getTransaction().rollback();
			throw new DAOException("Error al borrar usuario por id", e);
		}
		// emf.close();

	}

	@Override
	public boolean validar(Usuario usuario) {
		HibernateUsuario hibernateUsuario = (HibernateUsuario) usuario;

		System.out.println(hibernateUsuario.getUsername() + "username");
		System.out.println(hibernateUsuario.getPassword() + "passvar");
		System.out.println(((HibernateUsuario) findByUsername(hibernateUsuario.getUsername())).getPassword() + "passobj");

		Usuario user = findByUsername(hibernateUsuario.getUsername());

		// System.out.println("EL USUARIO ES" + user);

		if (user != null) {
			// System.out.println("validando");
			// System.out.println(((HibernateUsuario) findByUsername(hibernateUsuario.getUsername())).getPassword() +
			// "passobj");
			// System.out.println(hibernateUsuario.getPassword() + "passvar");

			String passApp, passObj;
			passApp = hibernateUsuario.getPassword();
			passObj = ((HibernateUsuario) findByUsername(hibernateUsuario.getUsername())).getPassword();

			return passApp.equals(passObj);
		} else {
			return false;
		}

	}

}
