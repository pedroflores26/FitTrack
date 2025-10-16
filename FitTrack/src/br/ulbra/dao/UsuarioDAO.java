package br.ulbra.dao;

import br.ulbra.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // --- SALVAR USUÁRIO ---
    public void salvar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuario (nome,idade, peso, senha, altura) VALUES (?, ?, ?, ?,?)";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getNome());    
            ps.setInt(2, u.getIdade());
            ps.setDouble(3, u.getPeso());
            ps.setString(4, u.getSenha());
            ps.setDouble(5, u.getAltura());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Falha ao inserir usuário.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                }
            }
        }
    }

    // --- BUSCAR USUÁRIO POR ID ---
 public Usuario buscarPorId(int id) {
    String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
    Usuario u = null;
    try (Connection con = AbstractDAO.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                u = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getInt("idade"),
                    rs.getDouble("peso"),
                    rs.getDouble("altura")
                );
            }
        }

    } catch (SQLException e) {
        e.printStackTrace(); // ou log
    }
    return u;
}


    // --- LISTAR TODOS OS USUÁRIOS ---
    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT * FROM usuario ORDER BY id_usuario";
        List<Usuario> lista = new ArrayList<>();

        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getInt("idade"),
                    rs.getDouble("peso"),
                    rs.getDouble("altura")
                );
                lista.add(u);
            }
        }
        return lista;
    }

    // --- ATUALIZAR USUÁRIO ---
    public void atualizar(Usuario u) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?,senha = ?, idade = ?, peso = ?, altura = ? WHERE id_usuario = ?";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setInt(3, u.getIdade());
            ps.setDouble(4, u.getPeso());
            ps.setDouble(5, u.getAltura());
            ps.setInt(6, u.getId());

            ps.executeUpdate();
        }
    }

    // --- REMOVER USUÁRIO ---
    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    public Usuario buscarPorNome(String nome) throws SQLException {
    Connection con = AbstractDAO.getConnection();
    String sql = "SELECT * FROM usuario WHERE nome = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, nome);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        Usuario u = new Usuario();
        u.setIdade(rs.getInt("idade"));
        u.setPeso(rs.getDouble("peso"));
        u.setAltura(rs.getDouble("altura"));
        u.setNome(rs.getString("nome"));
        u.setSenha(rs.getString("senha"));
        return u;
    }
    return null; // se não encontrou
}
}
