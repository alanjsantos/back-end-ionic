package com.api.ordering.controller;

import com.api.ordering.dto.CategoriaDTO;
import com.api.ordering.dto.ClienteNewDTO;
import com.api.ordering.model.Categoria;
import com.api.ordering.model.Cliente;
import com.api.ordering.model.Pedido;
import com.api.ordering.service.PedidoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido){
        pedido = pedidoService.save(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }


}
