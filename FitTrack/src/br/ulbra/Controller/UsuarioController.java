package br.ulbra.controller;

import br.ulbra.dao.UsuarioDAO;
import br.ulbra.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController{

    private UsuarioDAO dao = new UsuarioDAO();

    // --- SALVAR USUÁRIO ---
    public void salvar(Usuario u) throws SQLException {
        dao.salvar(u);
    }

    // --- BUSCAR USUÁRIO POR ID ---
    public Usuario buscar(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    // --- LISTAR TODOS OS USUÁRIOS ---
    public List<Usuario> listar() throws SQLException {
        return dao.listar();
    }

    // --- ATUALIZAR USUÁRIO ---
    public void atualizar(Usuario u) throws SQLException {
        dao.atualizar(u);
    }

    // --- REMOVER USUÁRIO ---
    public void remover(int id) throws SQLException {
        dao.remover(id);
    }
}
