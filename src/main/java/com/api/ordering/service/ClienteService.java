package com.api.ordering.service;


import com.api.ordering.dto.ClienteDTO;
import com.api.ordering.dto.ClienteNewDTO;

import com.api.ordering.model.Cidade;
import com.api.ordering.model.Cliente;
import com.api.ordering.model.Endereco;
import com.api.ordering.model.enums.TipoCliente;

import com.api.ordering.repository.ClienteRepository;
import com.api.ordering.repository.EnderecoRepository;
import com.api.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> findAll (){

        return clienteRepository.findAll();
    }

    public Cliente findId(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "O Objeto não foi encontrado. " + id + " Tipo: " + Cliente.class.getName()
        ));
    }
    @Transactional
    public Cliente save(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());

        return cliente;
    }

    public Cliente update(Cliente cliente){

        Cliente newCliente = findId(cliente.getId());
        updateData(newCliente, cliente);

        return clienteRepository.save(newCliente);

    }

    public void delete(Integer id){
        findId(id);
        try {
            clienteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw  new com.api.ordering.service.exception.DataIntegrityViolationException("" +
                    "Não é possível exlcuir: Há entidades relacionadas.");
        }

    }

    //Listagem com Paginação
        /*
           Parametros da Classe Page:
           1. Qual a pagina que eu quero? - Integer page (pagina 1, pagina 2, etc)
           2. Quantas linhas poçr pagina? - Integer linesPerPage
           3. Qual a atributo irei ordernar? - String orderBy (Por ID, por NOME, etc)
           4. Qual a direção eu vou ordenar?  direction (Se e´descendente ou ascendente)
         */
    public Page<Cliente> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return clienteRepository.findAll(pageRequest);
    }

    //Convertendo entidade para DTO.
    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    //Convertendo entidade para DTO
    public Cliente ClienteNewDTOFromDTO(ClienteNewDTO clienteNewDTO){
         Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipo()));
         Cidade cid = new Cidade(clienteNewDTO.getCidadeId(), null, null);
         Endereco end = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cli, cid);
         //Vinculando o cliente ao endereoc
         cli.getEnderecos().add(end);
         //Vinclando o cliente ao telefone
         cli.getTelefones().add(clienteNewDTO.getTelefone1());

         if (clienteNewDTO.getTelefone2() != null){
             cli.getTelefones().add(clienteNewDTO.getTelefone2());
         }

         if (clienteNewDTO.getTeelfone3() != null){
             cli.getTelefones().add(clienteNewDTO.getTeelfone3());
         }

         return cli;

    }

    //Atualizando o campo nome e email quando o metodo update for acionado.
    private void updateData (Cliente newCliente, Cliente cliente){
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }
}
