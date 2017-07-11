package com.ipartek.formacion.ijimenez.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.ijimenez.tipos.Factura;
import com.ipartek.formacion.ijimenez.tipos.FacturaLinea;
import com.ipartek.formacion.ijimenez.tipos.Producto;

public class FacturaDAOMySQL extends IpartekDAOMySQL implements FacturaDAO {
	private final static String FIND_ALL = "SELECT * FROM facturas";
	private final static String FIND_BY_ID = "SELECT * FROM facturas WHERE id = ?";
	private final static String INSERT = "INSERT INTO facturas (numero_factura, id_usuarios, fecha)" + " VALUES (?, ?, ?)";
	private final static String UPDATE = "UPDATE facturas " + "SET numero_factura = ?, id_usuarios = ?,fecha = ?" + "WHERE id = ?";
	private final static String DELETE = "DELETE FROM facturas WHERE id = ?";

	private final static String FIND_ALL_LINEAS = "SELECT * FROM facturas_productos WHERE id_facturas = ?";

	private PreparedStatement psFindAll, psFindById, psInsert, psUpdate, psDelete, psFindAllLineas;

	public Factura[] findAll() {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		ResultSet rs = null;

		try {
			psFindAll = con.prepareStatement(FIND_ALL);

			rs = psFindAll.executeQuery();

			Factura factura;

			while (rs.next()) {
				factura = new Factura();

				factura.setId(rs.getInt("id"));
				factura.setNúmero_factura(rs.getString("numero_factura"));
				factura.setId_usuarios(rs.getInt("id_usuarios"));
				factura.setFecha(rs.getDate("fecha"));

				facturas.add(factura);
			}

		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrar(psFindAll, rs);
		}
		return facturas.toArray(new Factura[facturas.size()]);
	}

	public Factura findById(int id) {
		Factura factura = null;
		ResultSet rs = null;

		try {
			psFindById = con.prepareStatement(FIND_BY_ID);

			psFindById.setInt(1, id);
			rs = psFindById.executeQuery();

			if (rs.next()) {
				factura = new Factura();

				factura.setId(rs.getInt("id"));
				factura.setNúmero_factura(rs.getString("numero_factura"));
				factura.setId_usuarios(rs.getInt("id_usuarios"));
				factura.setFecha(rs.getDate("fecha"));
			}

		} catch (SQLException e) {
			throw new DAOException("Error en FindById", e);
		} finally {
			cerrar(psFindById, rs);
		}
		return factura;
	}

	public int insert(Factura factura) {
		ResultSet generatedKeys = null;

		try {
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, factura.getNúmero_factura());
			psInsert.setInt(2, factura.getId_usuarios());
			psInsert.setDate(3, new java.sql.Date(factura.getFecha().getTime()));

			int res = psInsert.executeUpdate();

			if (res != 1) {
				throw new DAOException("La inserción ha devuelto un valor " + res);
			}
			generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				throw new DAOException("No se ha recibido la clave generada");
			}

		} catch (SQLException e) {
			throw new DAOException("Error en el insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}

	}

	public void update(Factura factura) {

		try {
			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setString(1, factura.getNúmero_factura());
			psUpdate.setInt(2, factura.getId_usuarios());
			psUpdate.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
			psUpdate.setInt(4, factura.getId());

			int res = psUpdate.executeUpdate();

			if (res != 1) {
				throw new DAOException("La actualización ha devuelto un valor " + res);
			}
		} catch (Exception e) {
			throw new DAOException("Error en el update", e);
		} finally {
			cerrar(psUpdate);
		}

	}

	public void delete(Factura factura) {
		delete(factura.getId());
	}

	public void delete(int id) {
		try {
			psDelete = con.prepareStatement(DELETE);

			psDelete.setInt(1, id);

			int res = psDelete.executeUpdate();

			if (res != 1) {
				throw new DAOException("La eliminación por ID ha devuelto un valor " + res);
			}
		} catch (Exception e) {
			throw new DAOException("Error en el deleteID", e);
		} finally {
			cerrar(psDelete);
		}

	}

	private void cerrar(PreparedStatement ps) {
		cerrar(ps, null);
	}

	private void cerrar(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			throw new DAOException("Error en el cierre de ps o rs", e);
		}
	}

	public void insertLinea(FacturaLinea linea) {
		// TODO Auto-generated method stub

	}

	public void deleteLinea(Producto producto) {
		// TODO Auto-generated method stub

	}

	public void updateLinea(FacturaLinea linea) {
		// TODO Auto-generated method stub

	}

	public void findLineaByProductoId(int idFactura, int idProducto) {
		// TODO Auto-generated method stub

	}

	public FacturaLinea[] findAllLineas(int idFactura) {
		ArrayList<FacturaLinea> lineas = new ArrayList<FacturaLinea>();

		try {
			psFindAllLineas = con.prepareStatement(FIND_ALL_LINEAS);

			psFindAllLineas.setInt(1, idFactura);

			ResultSet rs = psFindAllLineas.executeQuery();

			ProductosDAL dao = new ProductosDALColeccion();
			dao.reutilizarConexion(this);

			while (rs.next()) {
				lineas.add(new FacturaLinea(dao.findById(rs.getInt("id_productos")), rs.getInt("cantidad")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineas.toArray(new FacturaLinea[lineas.size()]);
	}

	public Factura findByIdFacturaCompleta(int id) {
		Factura factura = findById(id);
		for (FacturaLinea fl : findAllLineas(factura.getId())) {
			factura.addProductoYCantidad(fl.getProducto(), fl.getCantidad());
		}
		return factura;
	}
}
