package br.com.pessoal.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private Integer id;
    private String nome;
    private List<Produto> produtos = new ArrayList<Produto>();

    public Categoria (Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return String.format("id=%d, nome=%s", this.id, this.nome);
    }

	public void adicionar(Produto produto) {
        this.produtos.add(produto);
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

}