package com.ipartek.formacion.ijimenez.dal;

public class DALFactory {
	public static ProductosDAL getProductosDAL() {
		return new ProductosDALColeccion();
	}

	public static UsuarioDAO getUsuariosDAL() {
		// return new UsuariosDALUsuarioUnico();
		// return new UsuariosDALColeccion();
		return new UsuarioDAOMySQL();
	}

	public static FacturaDAO getFacturasDAO() {
		return new FacturaDAOMySQL();
	}

}
