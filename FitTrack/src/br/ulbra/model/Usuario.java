package br.ulbra.model;

public class Usuario {

    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private int idade;
    private double peso;
    private double altura;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nome, String email, String senha, int idade, double peso, double altura) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
    }

    // Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    // Método auxiliar (opcional): calcula o IMC
    public double calcularIMC() {
        if (altura > 0) {
            return peso / (altura * altura);
        } else {
            return 0;
        }
    }
}
