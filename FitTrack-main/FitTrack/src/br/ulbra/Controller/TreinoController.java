package br.ulbra.Controller;

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
     // Novo método: lista treinos por usuário
    public List<Treino> listarPorUsuario(int idUsuario) throws SQLException {
        return dao.listarPorUsuario(idUsuario);
    }

    // --- ATUALIZAR TREINO ---
    public void atualizar(Treino t) throws SQLException {
        dao.atualizar(t);
    }

     public void removerPorId(int id) throws SQLException {
        // opcional: validação se o treino existe
        Treino t = dao.buscarPorId(id);
        if (t == null) {
            throw new SQLException("Treino não encontrado para o ID: " + id);
        }
        dao.remover(id);
    }
}