package br.ulbra.model;



public class Treino {

    private int idTreino;
    private String tipo;
    private int duracao;
    private int calorias;
    private String dataTreino;
    private int idUsuario;

    public Treino() {
    }

    public Treino(int idTreino, String tipo, int duracao, int calorias, String dataTreino, int idUsuario) {
        this.idTreino = idTreino;
        this.tipo = tipo;
        this.duracao = duracao;
        this.calorias = calorias;
        this.dataTreino = dataTreino;
        this.idUsuario = idUsuario;
    }

    public int getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(int idTreino) {
        this.idTreino = idTreino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public String getDataTreino() {
        return dataTreino;
    }

    public void setDataTreino(String dataTreino) {
        this.dataTreino = dataTreino;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}