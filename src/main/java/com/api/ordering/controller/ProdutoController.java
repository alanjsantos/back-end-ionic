package com.api.ordering.controller;

import com.api.ordering.controller.utils.URL;
import com.api.ordering.dto.CategoriaDTO;
import com.api.ordering.dto.ProdutoDTO;
import com.api.ordering.model.Categoria;
import com.api.ordering.model.Pedido;
import com.api.ordering.model.Produto;
import com.api.ordering.repository.CategoriaRepository;
import com.api.ordering.service.PedidoService;
import com.api.ordering.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/{id}")
    ResponseEntity<Produto> buscarPorId(@PathVariable Integer id){
        Produto produto = produtoService.buscarPorId(id);

        return ResponseEntity.ok().body(produto);
    }
    //Paginação
    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        String decodeParam = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = produtoService.search(decodeParam,  ids, page, linesPerPage, orderBy, direction);

        //convertendo  um page de Produto para um pageDTO
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }


}
