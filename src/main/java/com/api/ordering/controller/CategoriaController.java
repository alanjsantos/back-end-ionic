package com.api.ordering.controller;

import com.api.ordering.model.Categoria;
import com.api.ordering.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Categoria categoria = service.buscarPorId(id);

        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria){
        Categoria cat = service.salvar(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(cat);
    }
}
