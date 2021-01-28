package com.api.ordering.service;

import com.api.ordering.model.Pedido;
import com.api.ordering.repository.PedidoRepository;
import com.api.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarPorId(Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Obejto não encontrado Id: " + id + "Tipo: " + Pedido.class.getName()));
    }
}
