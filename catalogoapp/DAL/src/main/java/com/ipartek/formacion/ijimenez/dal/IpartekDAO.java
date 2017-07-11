package com.ipartek.formacion.ijimenez.dal;


public interface IpartekDAO {
	public void abrir();

	public void cerrar();

	public void reutilizarConexion(IpartekDAO dao);

	public void iniciarTransaccion();

	public void confirmarTransaccion();

	public void deshacerTransaccion();
}
