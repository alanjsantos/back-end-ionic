package com.api.ordering.model;

import com.api.ordering.model.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 2097261343722473743L;

    @Id
    private Integer id;
    private Integer estado;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId //em um mapemaento de um para um, as vezes é necessário colocar a anotação MasId para que a classe pai e filha tenham um mesmo ID.
    private Pedido pedido;

    public Pagamento(){

    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCod();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return id.equals(pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
