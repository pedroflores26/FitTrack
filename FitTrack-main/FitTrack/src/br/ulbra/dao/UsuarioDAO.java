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
        String sql = "INSERT INTO usuario (nome, idade, peso, senha, altura) VALUES (?, ?, ?, ?, ?)";
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
                    u.setId(rs.getInt(1)); // garante que o objeto recebe o ID gerado
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
            e.printStackTrace();
        }
        return u;
    }

    // --- BUSCAR USUÁRIO POR NOME ---
    public Usuario buscarPorNome(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome = ?";
        Usuario u = null;
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nome);
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
            e.printStackTrace();
        }
        return u;
    }

    // --- LISTAR TODOS OS USUÁRIOS ---
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY id_usuario";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getInt("idade"),
                        rs.getDouble("peso"),
                        rs.getDouble("altura")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // --- ATUALIZAR USUÁRIO ---
    public void atualizar(Usuario u) {
        String sql = "UPDATE usuario SET nome = ?, senha = ?, idade = ?, peso = ?, altura = ? WHERE id_usuario = ?";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setInt(3, u.getIdade());
            ps.setDouble(4, u.getPeso());
            ps.setDouble(5, u.getAltura());
            ps.setInt(6, u.getId());

            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- REMOVER USUÁRIO ---
    public void remover(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection con = AbstractDAO.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
