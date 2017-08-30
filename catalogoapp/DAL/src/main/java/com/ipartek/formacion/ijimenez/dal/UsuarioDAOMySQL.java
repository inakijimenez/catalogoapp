package com.ipartek.formacion.ijimenez.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.ijimenez.tipos.Usuario;
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

	public List<Usuario> findAll() {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
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
		return usuarios;
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

	public Usuario findById(int id) {
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

	public Usuario findByUsername(String username) {
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

	public int insert(Usuario usuario) {
		ResultSet generatedKeys = null;

		try {

			UsuarioMySQL usuarioMySQL = (UsuarioMySQL) usuario;
			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, usuarioMySQL.getUsername());
			psInsert.setString(2, usuarioMySQL.getPassword());
			psInsert.setString(3, usuarioMySQL.getNombre_completo());
			psInsert.setInt(4, usuarioMySQL.getId_roles());

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

	public void update(Usuario usuario) {
		try {
			UsuarioMySQL usuarioMySQL = (UsuarioMySQL) usuario;
			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setString(1, usuarioMySQL.getUsername());
			psUpdate.setString(2, usuarioMySQL.getPassword());
			psUpdate.setString(3, usuarioMySQL.getNombre_completo());
			psUpdate.setInt(4, usuarioMySQL.getId_roles());

			psUpdate.setInt(5, usuarioMySQL.getId());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrar(psUpdate);
		}
	}

	public void delete(Usuario usuario) {
		UsuarioMySQL usuarioMySQL = (UsuarioMySQL) usuario;
		delete(usuarioMySQL.getId());
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

	public boolean validar(Usuario usuario) {

		// verificar que el usuario existe
		// System.out.println(usuario + " usuario en validar");
		UsuarioMySQL usuarioMySQL = (UsuarioMySQL) usuario;

		if (findByUsername(usuarioMySQL.getUsername()) != null) {
			return ((UsuarioMySQL) findByUsername(usuarioMySQL.getUsername())).getPassword().equals(usuarioMySQL.getPassword());
		} else {
			return false;
		}
	}
}
