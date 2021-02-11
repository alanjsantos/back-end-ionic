package com.api.ordering.controller;

import com.api.ordering.dto.ClienteDTO;
import com.api.ordering.model.Cliente;
import com.api.ordering.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //Buscando uma lista de cliente
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = clienteService.findAll();

        //convertendo uma lista cliente para uma listaDTO
        List<ClienteDTO> listDto = list.stream()
                .map(obj -> new ClienteDTO(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    //Buscando cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findId(@PathVariable Integer id){
        Cliente cliente = clienteService.findId(id);

        return ResponseEntity.ok().body(cliente);
    }
    //Atualizando cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setId(id);
        cliente = clienteService.update(cliente);

        return ResponseEntity.noContent().build();
    }

    //Deletando cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Integer id){
        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }

    //Paginação
    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
        //convertendo  um page de cliente para um pageDTO
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }

}
