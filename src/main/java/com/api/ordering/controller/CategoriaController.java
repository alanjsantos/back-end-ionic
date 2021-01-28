package com.api.ordering.controller;

import com.api.ordering.model.Categoria;
import com.api.ordering.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService cargoservice;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findId(@PathVariable Integer id) {
        Categoria categoria = cargoservice.findId(id);

        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Categoria categoria){
        categoria = cargoservice.save(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Categoria categoria, @PathVariable Integer id){
        categoria.setId(id);
        categoria = cargoservice.update(categoria);

        return ResponseEntity.noContent().build();
    }
}
