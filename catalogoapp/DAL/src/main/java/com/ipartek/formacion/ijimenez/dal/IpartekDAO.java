package com.ipartek.formacion.ijimenez.dal;

public interface IpartekDAO {
	public void abrir();
	public void cerrar();
	
	public void iniciarTransaccion();
	public void confirmarTransaccion();
	public void deshacerTransaccion();
}
