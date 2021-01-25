package com.api.ordering.controller;

import com.api.ordering.model.Cliente;
import com.api.ordering.service.ClienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Cliente cliente = clienteService.buscarPorId(id);

        return ResponseEntity.ok().body(cliente);
    }

}
