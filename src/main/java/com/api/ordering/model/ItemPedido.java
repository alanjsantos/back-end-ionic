package com.api.ordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = -3484404038238243930L;

    //Classe Auxiliar
    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido(){

    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        this.desconto = desconto;
        this.id = id;
        id.setPedido(pedido);
        id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = preco;
    }
    //metodo que calcula o subtotal de um pedido
    public double getSubTotal(){
        return (preco - desconto) * quantidade;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    //metodo para associar o pedido ao itemPedido
    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }

    @JsonIgnore
    public Produto getProduto(){
        return id.getProduto();
    }

    //metodo para associar o pedido ao itemPedido
    public void setProduto(Produto produto){
        id.setProduto(produto);
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
