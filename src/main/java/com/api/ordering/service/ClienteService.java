package com.api.ordering.service;

import com.api.ordering.model.Categoria;
import com.api.ordering.model.Cliente;
import com.api.ordering.repository.ClienteRepository;
import com.api.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente buscarPorId(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "O Objeto não foi encontrado. " + id + "Tipo: " + Cliente.class.getName()
        ));
    }
}
