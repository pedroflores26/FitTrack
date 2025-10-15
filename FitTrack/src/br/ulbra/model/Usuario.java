package br.ulbra.model;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private int idade;
    private double peso;
    private double altura;

    public Usuario() {
    }

    public Usuario(int id, String nome, String senha, int idade, double peso, double altura) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
    }

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
}
