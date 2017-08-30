package com.ipartek.formacion.ijimenez.dal;

public class DALFactory {
	public static ProductosDAL getProductosDAL() {
		// return new ProductosDALColeccion();
		return new ProductosDALHibernate();
	}

	public static UsuarioDAO getUsuariosDAL() {
		// return new UsuariosDALUsuarioUnico();
		// return new UsuariosDALColeccion();
		// return new UsuarioDAOMySQL();
		return new UsuarioDAOHibernate();
	}

	public static FacturaDAO getFacturasDAO() {
		// return new FacturaDAOMySQL();
		return new FacturaDAOHibernate();
	}

	public static FacturaLineaDAOHibernate getFacturaLineaDAO() {

		return new FacturaLineaDAOHibernate();
	}
}
