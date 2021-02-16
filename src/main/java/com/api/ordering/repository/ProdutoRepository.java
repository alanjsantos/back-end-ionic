package com.api.ordering.repository;

import com.api.ordering.model.Categoria;
import com.api.ordering.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


    Page<Produto> findDistinctByNomeContainingAndCategoriasIn (String nome, List<Categoria> categorias, Pageable pageRequest);

}
