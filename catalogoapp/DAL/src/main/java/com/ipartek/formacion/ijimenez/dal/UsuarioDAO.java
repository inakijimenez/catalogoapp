package com.ipartek.formacion.ijimenez.dal;

import java.util.List;

import com.ipartek.formacion.ijimenez.tipos.Usuario;

public interface UsuarioDAO extends IpartekDAO {
	public List<Usuario> findAll();

	public Usuario findById(int id);

	public int insert(Usuario usuario);

	public void update(Usuario usuario);

	public void delete(Usuario usuario);

	public void delete(int id);

	public boolean validar(Usuario usuario);
}
