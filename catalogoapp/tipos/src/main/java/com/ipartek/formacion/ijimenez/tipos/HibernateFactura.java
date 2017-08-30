package com.ipartek.formacion.ijimenez.tipos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "FACTURAS")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "FACTURA_SEQ", sequenceName = "factura_sequence")
public class HibernateFactura extends Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACTURA_SEQ")
	@Column(name = "ID_FACTURA")
	private int id;

	@Column(name = "NUM_FACTURA")
	@Basic(optional = false)
	private String numero_factura;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private HibernateUsuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA")
	private Date fecha;

	@OneToMany(mappedBy = "factura")
	private Collection<HibernateFacturaLinea> facturaLinea = new ArrayList<HibernateFacturaLinea>();

	public HibernateFactura() {
		super();
	}

	public HibernateFactura(HibernateUsuario usuario, Date fecha) {
		super();

		this.usuario = usuario;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNúmero_factura() {
		return numero_factura;
	}

	public void setNúmero_factura(String número_factura) {
		this.numero_factura = número_factura;
	}

	public HibernateUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(HibernateUsuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Collection<HibernateFacturaLinea> getFacturaLinea() {
		return facturaLinea;
	}

	public void setFacturaLinea(Collection<HibernateFacturaLinea> facturaLinea) {
		this.facturaLinea = facturaLinea;
	}

	@Override
	public String toString() {
		return "HibernateFactura [id=" + id + ", número_factura=" + numero_factura + ", usuario=" + usuario + ", fecha=" + fecha + "]";
	}

}
