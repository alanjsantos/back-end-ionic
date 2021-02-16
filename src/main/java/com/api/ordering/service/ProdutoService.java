package com.api.ordering.service;

import com.api.ordering.model.Categoria;
import com.api.ordering.model.Pedido;
import com.api.ordering.model.Produto;
import com.api.ordering.repository.CategoriaRepository;
import com.api.ordering.repository.PedidoRepository;
import com.api.ordering.repository.ProdutoRepository;
import com.api.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto buscarPorId(Integer id){
        Optional<Produto> pedido = produtoRepository.findById(id);

        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Obejto não encontrado Id: " + id + "Tipo: " + Produto.class.getName()));
    }

    //Listagem com Paginação
        /*
           Parametros da Classe Page:
           1. Qual a pagina que eu quero? - Integer page (pagina 1, pagina 2, etc)
           2. Quantas linhas poçr pagina? - Integer linesPerPage
           3. Qual a atributo irei ordernar? - String orderBy (Por ID, por NOME, etc)
           4. Qual a direção eu vou ordenar?  direction (Se e´descendente ou ascendente)
         */
    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage,
                                String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

    }
}
