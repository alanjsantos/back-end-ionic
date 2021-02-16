package com.api.ordering.dto;

import com.api.ordering.model.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 3153304612594152698L;

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO(){

    }

    //Convertendo entidade para DTO
    public ProdutoDTO (Produto dto){
        id = dto.getId();
        nome = dto.getNome();
        preco = dto.getPreco();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
