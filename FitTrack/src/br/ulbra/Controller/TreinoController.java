package br.ulbra.controller;

import br.ulbra.dao.TreinoDAO;
import br.ulbra.model.Treino;
import java.sql.SQLException;
import java.util.List;

public class TreinoController {

    private TreinoDAO dao = new TreinoDAO();

    // --- SALVAR TREINO ---
    public void salvar(Treino t) throws SQLException {
        dao.salvar(t);
    }

    // --- BUSCAR TREINO POR ID ---
    public Treino buscar(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    // --- LISTAR TODOS OS TREINOS ---
    public List<Treino> listar() throws SQLException {
        return dao.listar();
    }

    // --- ATUALIZAR TREINO ---
    public void atualizar(Treino t) throws SQLException {
        dao.atualizar(t);
    }

    // --- REMOVER TREINO ---
    public void remover(int id) throws SQLException {
        dao.remover(id);
    }
}
