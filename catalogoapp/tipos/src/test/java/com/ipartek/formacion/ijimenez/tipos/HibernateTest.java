package com.ipartek.formacion.ijimenez.tipos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateTest {

	public static void main(String[] args) {

		EntityManager manager;
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();

	}

}
