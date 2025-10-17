package br.ulbra.dao;


import br.ulbra.model.Treino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {

    // --- MÉTODO PARA INSERIR UM TREINO ---
    public void salvar(Treino t) throws SQLException {
        String sql = "INSERT INTO treino (tipo, duracao, calorias, data_treino, id_usuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, t.getTipo());
            ps.setInt(2, t.getDuracao());
            ps.setInt(3, t.getCalorias());
            ps.setString(4, t.getDataTreino());
            ps.setInt(5, t.getIdUsuario());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Falha ao inserir treino.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    t.setIdTreino(rs.getInt(1));
                }
            }
        }
    }

   

   // --- MÉTODO PARA LISTAR TODOS OS TREINOS ---
public List<Treino> listar() throws SQLException {
    String sql = "SELECT * FROM treino ORDER BY id_treino";
    List<Treino> lista = new ArrayList<>();

    try (Connection con = AbstractDAO.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Treino t = new Treino(
                rs.getInt("id_treino"),
                rs.getString("tipo"),
                rs.getInt("duracao"),
                rs.getInt("calorias"),
                rs.getString("data_treino"),
                rs.getInt("id_usuario")
            );
            lista.add(t);
        }
    }
    return lista;
}

// --- MÉTODO PARA LISTAR TREINOS DE UM USUÁRIO ESPECÍFICO ---
public List<Treino> listarPorUsuario(int idUsuario) throws SQLException {
    String sql = "SELECT * FROM treino WHERE id_usuario = ? ORDER BY id_treino";
    List<Treino> lista = new ArrayList<>();

    try (Connection con = AbstractDAO.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idUsuario);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Treino t = new Treino(
                    rs.getInt("id_treino"),
                    rs.getString("tipo"),
                    rs.getInt("duracao"),
                    rs.getInt("calorias"),
                    rs.getString("data_treino"),
                    rs.getInt("id_usuario")
                );
                lista.add(t);
            }
        }
    }
    return lista;
}

    // --- MÉTODO PARA ATUALIZAR UM TREINO ---
    public void atualizar(Treino t) throws SQLException {
        String sql = "UPDATE treino SET tipo = ?, duracao = ?, calorias = ?, data_treino = ?, id_usuario = ? WHERE id_treino = ?";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getTipo());
            ps.setInt(2, t.getDuracao());
            ps.setInt(3, t.getCalorias());
            ps.setString(4, t.getDataTreino());
            ps.setInt(5, t.getIdUsuario());
            ps.setInt(6, t.getIdTreino());
            ps.executeUpdate();
        }
    }

      public void remover(int id) throws SQLException {
        String sql = "DELETE FROM treino WHERE id_treino = ?";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // (Opcional) buscar por ID já existente - se precisar em algum fluxo
    public Treino buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM treino WHERE id_treino = ?";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Treino(
                        rs.getInt("id_treino"),
                        rs.getString("tipo"),
                        rs.getInt("duracao"),
                        rs.getInt("calorias"),
                        rs.getString("data_treino"),
                        rs.getInt("id_usuario")
                    );
                }
            }
        }
        return null;
    }
}