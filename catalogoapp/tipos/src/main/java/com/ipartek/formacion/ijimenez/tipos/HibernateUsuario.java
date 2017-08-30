package com.ipartek.formacion.ijimenez.tipos;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity(name = "USUARIOS")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "USUARIO_SEQ", sequenceName = "usuario_sequence")
public class HibernateUsuario extends Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	@Column(name = "ID_USUARIO")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ROL")
	private HibernateRol rol;

	@OneToMany(mappedBy = "usuario")
	private Collection<HibernateFactura> factura = new ArrayList<HibernateFactura>();

	@Basic(optional = false)
	@Lob
	@Column(name = "NOMBRE_COMPLETO")
	private String nombre_completo;

	@Basic(optional = false)
	@Column(name = "PASSWORD")
	private String password;

	@Basic(optional = false)
	@Column(name = "USERNAME", unique = true)
	private String username;

	@Transient
	private String errores;

	public HibernateUsuario() {
		super();
	}

	public HibernateUsuario(int rol, String nombre_completo, String password, String username) {

		this.nombre_completo = nombre_completo;
		this.password = password;
		this.username = username;

		HibernateRol roles = new HibernateRol();
		this.rol = roles.findById(rol);

	}

	public HibernateRol getRol() {
		return rol;
	}

	public void setRol(HibernateRol rol) {
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getErrores() {
		return errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	public Collection<HibernateFactura> getFactura() {
		return factura;
	}

	public void setFactura(Collection<HibernateFactura> factura) {
		this.factura = factura;
	}

	@Override
	public String toString() {
		return "HibernateUsuario [id=" + id + ", nombre_completo=" + nombre_completo + ", password=" + password + ", username=" + username + ", errores=" + errores + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre_completo == null) ? 0 : nombre_completo.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HibernateUsuario other = (HibernateUsuario) obj;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (id != other.id)
			return false;
		if (nombre_completo == null) {
			if (other.nombre_completo != null)
				return false;
		} else if (!nombre_completo.equals(other.nombre_completo))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
