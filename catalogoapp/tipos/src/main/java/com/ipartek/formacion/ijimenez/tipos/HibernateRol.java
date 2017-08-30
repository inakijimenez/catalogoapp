package com.ipartek.formacion.ijimenez.tipos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;

@Entity(name = "ROLES")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "ROL_SEQ", sequenceName = "rol_sequence")
public class HibernateRol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_ROL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_SEQ")
	private int idRol;

	@Column(name = "NOMBRE", unique = true)
	@Basic(optional = false)
	private String nombreRol;

	@Column(name = "DESCRIPCION")
	@Lob
	private String descripcionRol;

	@OneToMany(mappedBy = "rol")
	private Collection<HibernateUsuario> usuario = new ArrayList<HibernateUsuario>();

	public Collection<HibernateUsuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<HibernateUsuario> usuario) {
		this.usuario = usuario;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public HibernateRol findById(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		HibernateRol rol = manager.find(HibernateRol.class, id);
		manager.getTransaction().commit();
		emf.close();

		return rol;
	}

}
