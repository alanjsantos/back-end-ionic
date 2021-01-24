package com.api.ordering.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @GetMapping
    public String listar(){
        return "Resta está funcionando.";
    }
}
