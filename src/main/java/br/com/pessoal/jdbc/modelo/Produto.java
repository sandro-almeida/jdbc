package br.com.pessoal.jdbc.modelo;


public class Produto {

    private Integer id;
    private String nome;
    private String descricao;

    public Produto (String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto (Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getId () {
        return this.id;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getNome () {
        return this.nome;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao () {
        return this.descricao;
    }

    @Override
    public String toString() {
        return String.format("id=%d, nome=%s, descricao=%s", this.id, this.nome, this.descricao);
    }

}