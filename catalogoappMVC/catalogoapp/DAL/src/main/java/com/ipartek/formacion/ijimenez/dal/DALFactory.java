package com.ipartek.formacion.ijimenez.dal;


public class DALFactory {
	public static ProductosDAL getProductosDAL() {
		return new ProductosDALColeccion();
	}

	public static UsuariosDAL getUsuariosDAL() {
		// return new UsuariosDALUsuarioUnico();
		return new UsuariosDALColeccion();
	}
}
