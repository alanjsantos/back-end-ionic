package com.api.ordering.controller;

import com.api.ordering.model.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @GetMapping
    public List<Categoria> listar(){
        Categoria categoria1 = new Categoria(1, "Informatica");
        Categoria categoria2 = new Categoria(2, "Mouse");

        List<Categoria> lista = new ArrayList<>();
        lista.add(categoria1);
        lista.add(categoria2);
        return lista;
    }
}
