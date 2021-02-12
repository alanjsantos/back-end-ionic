package com.api.ordering.repository;

import com.api.ordering.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //Metodo que buscar o email. Este metodo é usado para validacao em ClienteValitador.
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
