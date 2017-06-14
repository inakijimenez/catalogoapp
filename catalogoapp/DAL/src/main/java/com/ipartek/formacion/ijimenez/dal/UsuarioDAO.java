package com.ipartek.formacion.ijimenez.dal;

import com.ipartek.formacion.ijimenez.tipos.UsuarioMySQL;

public interface UsuarioDAO extends IpartekDAO {
	public UsuarioMySQL[] findAll();

	public UsuarioMySQL findById(int id);

	public int insert(UsuarioMySQL usuario);

	public void update(UsuarioMySQL usuario);

	public void delete(UsuarioMySQL usuario);

	public void delete(int id);

	public boolean validar(UsuarioMySQL usuario);
}
