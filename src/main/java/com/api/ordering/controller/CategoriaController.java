package com.api.ordering.controller;

import com.api.ordering.dto.CategoriaDTO;
import com.api.ordering.model.Categoria;
import com.api.ordering.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaservice;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> list = categoriaservice.findAll();

        //convertendo uma lista categoria para uma listaDTO
        List<CategoriaDTO> listDto = list.stream()
                .map(obj -> new CategoriaDTO(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findId(@PathVariable Integer id) {
        Categoria categoria = categoriaservice.findId(id);

        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@Valid @RequestBody CategoriaDTO categoriaDTO){
       Categoria categoria = categoriaservice.fromDTO(categoriaDTO);
       categoria = categoriaservice.save(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
        Categoria categoria = categoriaservice.fromDTO(categoriaDTO);
        categoria.setId(id);
        categoria = categoriaservice.update(categoria);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable Integer id){
        categoriaservice.delete(id);

        return ResponseEntity.noContent().build();
    }

    //Buscar Paginada (Paginação)
    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Categoria> list = categoriaservice.findPage(page, linesPerPage, orderBy, direction);
        //convertendo  um page de categoria para um pageDTO
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }

}
