package com.api.ordering.controller;

import com.api.ordering.model.Pedido;
import com.api.ordering.service.PedidoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    ResponseEntity<Pedido> buscarPorId(@PathVariable Integer id){
        Pedido pedido = pedidoService.buscarPorId(id);

        return ResponseEntity.ok().body(pedido);
    }


}
