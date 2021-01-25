package com.api.ordering.service;

import com.api.ordering.model.Categoria;
import com.api.ordering.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscarPorId(Integer id){
        Optional<Categoria> categoria = repository.findById(id);

        return categoria.orElse(null);
    }
}
