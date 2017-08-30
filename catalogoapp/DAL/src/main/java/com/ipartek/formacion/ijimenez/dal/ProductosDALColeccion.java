package com.ipartek.formacion.ijimenez.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.ijimenez.tipos.Producto;

public class ProductosDALColeccion extends IpartekDAOMySQL implements ProductosDAL {

	private final static String FIND_ALL = "SELECT * FROM productos";
	private final static String FIND_BY_ID = "SELECT * FROM productos WHERE id = ?";
	private final static String FIND_BY_USERNAME = "SELECT * FROM productos WHERE nombre = ?";
	private final static String INSERT = "INSERT INTO productos (id, nombre, precio, descripcion )" + " VALUES (?, ?, ?, ?)";
	private final static String UPDATE = "UPDATE productos " + "SET nombre = ?, precio = ?, descripcion = ?" + "WHERE id = ?";
	private final static String DELETE = "DELETE FROM productos WHERE id = ?";

	private PreparedStatement psFindAll, psFindById, psFindByUsername, psInsert, psUpdate, psDelete;

	public void insert(Producto producto) {
		ResultSet generatedKeys = null;

		try {
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setInt(1, producto.getId());
			psInsert.setString(2, producto.getNombre());
			psInsert.setDouble(3, producto.getPrecio());
			psInsert.setString(4, producto.getDescripcion());

			int res = psInsert.executeUpdate();

			if (res != 1)
				throw new DAOException("La inserción ha devuelto un valor " + res);

			generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next())
				generatedKeys.getInt(1);
			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (Exception e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}

	}

	public void update(Producto producto) {
		try {
			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setString(1, producto.getNombre());
			psUpdate.setDouble(2, producto.getPrecio());
			psUpdate.setString(3, producto.getDescripcion());

			psUpdate.setInt(4, producto.getId());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrar(psUpdate);
		}

	}

	public void delete(Producto producto) {
		delete(producto.getId());

	}

	public void delete(int id) {
		try {
			psDelete = con.prepareStatement(DELETE);

			psDelete.setInt(1, id);

			int res = psDelete.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrar(psDelete);
		}

	}

	public Producto findById(int id) {

		Producto producto = null;
		ResultSet rs = null;

		try {
			psFindById = con.prepareStatement(FIND_BY_ID);

			psFindById.setInt(1, id);
			rs = psFindById.executeQuery();

			if (rs.next()) {
				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setDescripcion(rs.getString("descripcion"));

			}

		} catch (Exception e) {
			throw new DAOException("Error en findById", e);
		} finally {
			cerrar(psFindById, rs);
		}

		return producto;
	}

	public List<Producto> findAll() {

		ArrayList<Producto> productos = new ArrayList<Producto>();
		ResultSet rs = null;

		try {
			psFindAll = con.prepareStatement(FIND_ALL);

			rs = psFindAll.executeQuery();

			Producto producto;

			while (rs.next()) {

				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setDescripcion(rs.getString("descripcion"));

				productos.add(producto);
			}

		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrar(psFindAll, rs);
		}
		return productos;
	}

	@Override
	public Producto findByName(String name) {
		Producto producto = null;
		ResultSet rs = null;

		try {
			psFindByUsername = con.prepareStatement(FIND_BY_USERNAME);

			psFindByUsername.setString(1, name);
			rs = psFindByUsername.executeQuery();

			if (rs.next()) {
				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setDescripcion(rs.getString("descripcion"));

			}

		} catch (Exception e) {
			throw new DAOException("Error en findByUsername", e);
		} finally {
			cerrar(psFindByUsername, rs);
		}

		return producto;
	}

	public boolean productoExiste(Producto producto) {

		return (findById(producto.getId()) != null);
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
}
