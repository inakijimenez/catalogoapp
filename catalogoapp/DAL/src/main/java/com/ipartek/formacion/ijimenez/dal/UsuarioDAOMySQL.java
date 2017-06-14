package com.ipartek.formacion.ijimenez.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.ijimenez.tipos.UsuarioMySQL;

public class UsuarioDAOMySQL extends IpartekDAOMySQL implements UsuarioDAO {

	private final static String FIND_ALL = "SELECT * FROM usuarios";
	private final static String FIND_BY_ID = "SELECT * FROM usuarios WHERE id = ?";
	private final static String FIND_BY_USERNAME = "SELECT * FROM usuarios WHERE username = ?";
	private final static String INSERT = "INSERT INTO usuarios (username, password, nombre_completo, id_roles)" + " VALUES (?, ?, ?, ?)";
	private final static String UPDATE = "UPDATE usuarios " + "SET username = ?, password = ?, nombre_completo = ?, id_roles = ? " + "WHERE id = ?";
	private final static String DELETE = "DELETE FROM usuarios WHERE id = ?";

	private PreparedStatement psFindAll, psFindById, psFindByUsername, psInsert, psUpdate, psDelete;

	public UsuarioDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		super(url, mysqlUser, mysqlPass);
	}

	public UsuarioDAOMySQL() {

	}

	public UsuarioMySQL[] findAll() {

		ArrayList<UsuarioMySQL> usuarios = new ArrayList<UsuarioMySQL>();
		ResultSet rs = null;

		try {
			psFindAll = con.prepareStatement(FIND_ALL);

			rs = psFindAll.executeQuery();

			UsuarioMySQL usuario;

			while (rs.next()) {
				// System.out.println(rs.getString("username"));
				usuario = new UsuarioMySQL();

				usuario.setId(rs.getInt("id"));
				usuario.setId_roles(rs.getInt("id_roles"));
				usuario.setNombre_completo(rs.getString("nombre_completo"));
				usuario.setPassword(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));

				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrar(psFindAll, rs);
		}
		return usuarios.toArray(new UsuarioMySQL[usuarios.size()]);
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

	public UsuarioMySQL findById(int id) {
		UsuarioMySQL usuario = null;
		ResultSet rs = null;

		try {
			psFindById = con.prepareStatement(FIND_BY_ID);

			psFindById.setInt(1, id);
			rs = psFindById.executeQuery();

			if (rs.next()) {
				usuario = new UsuarioMySQL();

				usuario.setId(rs.getInt("id"));
				usuario.setId_roles(rs.getInt("id_roles"));
				usuario.setNombre_completo(rs.getString("nombre_completo"));
				usuario.setPassword(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));
			}

		} catch (Exception e) {
			throw new DAOException("Error en findById", e);
		} finally {
			cerrar(psFindById, rs);
		}

		return usuario;
	}

	public UsuarioMySQL findByUsername(String username) {
		UsuarioMySQL usuario = null;
		ResultSet rs = null;

		try {
			psFindByUsername = con.prepareStatement(FIND_BY_USERNAME);

			// System.out.println(usuario);

			psFindByUsername.setString(1, username);
			rs = psFindByUsername.executeQuery();

			if (rs.next()) {
				usuario = new UsuarioMySQL();

				usuario.setId(rs.getInt("id"));
				usuario.setId_roles(rs.getInt("id_roles"));
				usuario.setNombre_completo(rs.getString("nombre_completo"));
				usuario.setPassword(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));
			}

		} catch (Exception e) {
			throw new DAOException("Error en findByUsername", e);
		} finally {
			cerrar(psFindByUsername, rs);
		}

		return usuario;
	}

	public int insert(UsuarioMySQL usuario) {
		ResultSet generatedKeys = null;

		try {
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, usuario.getUsername());
			psInsert.setString(2, usuario.getPassword());
			psInsert.setString(3, usuario.getNombre_completo());
			psInsert.setInt(4, usuario.getId_roles());

			int res = psInsert.executeUpdate();

			if (res != 1)
				throw new DAOException("La inserción ha devuelto un valor " + res);

			generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next())
				return generatedKeys.getInt(1);
			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (Exception e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}
	}

	public void update(UsuarioMySQL usuario) {
		try {
			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setString(1, usuario.getUsername());
			psUpdate.setString(2, usuario.getPassword());
			psUpdate.setString(3, usuario.getNombre_completo());
			psUpdate.setInt(4, usuario.getId_roles());

			psUpdate.setInt(5, usuario.getId());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrar(psUpdate);
		}
	}

	public void delete(UsuarioMySQL usuario) {
		delete(usuario.getId());
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

	public boolean validar(UsuarioMySQL usuario) {

		// verificar que el usuario existe
		// System.out.println(usuario + " usuario en validar");
		if (findByUsername(usuario.getUsername()) != null) {
			return findByUsername(usuario.getUsername()).getPassword().equals(usuario.getPassword());
		} else {
			return false;
		}
	}
}
