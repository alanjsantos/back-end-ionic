package com.api.ordering.service;

import com.api.ordering.model.Categoria;
import com.api.ordering.model.Produto;
import com.api.ordering.repository.CategoriaRepository;
import com.api.ordering.repository.ProdutoRepository;
import com.api.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }
    @Transactional
    public Categoria salvar(Categoria categoria) {
        Categoria cat = new Categoria(null, categoria.getNome());

        for (Produto p : categoria.getProdutos()) {
            Produto produto = produtoRepository.getOne(p.getId());
            cat.getProdutos().add(produto);
        }
        cat = categoriaRepository.save(cat);

        return cat;
    }
}