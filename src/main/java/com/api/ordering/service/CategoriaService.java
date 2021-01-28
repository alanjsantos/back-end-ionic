package com.api.ordering.service;

import com.api.ordering.model.Categoria;
import com.api.ordering.model.Produto;
import com.api.ordering.repository.CategoriaRepository;
import com.api.ordering.repository.ProdutoRepository;
import com.api.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Categoria findId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria save(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria){
        findId(categoria.getId());

        return categoriaRepository.save(categoria);

    }

    public void delete(Integer id){
        findId(id);
        try {
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw  new com.api.ordering.service.exception.DataIntegrityViolationException("" +
                    "Não é possível exlcuir uma garotia que possui Produto.");
        }

    }

}