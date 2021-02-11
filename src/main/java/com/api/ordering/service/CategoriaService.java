package com.api.ordering.service;

import com.api.ordering.dto.CategoriaDTO;
import com.api.ordering.model.Categoria;
import com.api.ordering.model.Cliente;
import com.api.ordering.model.Produto;
import com.api.ordering.repository.CategoriaRepository;
import com.api.ordering.repository.ProdutoRepository;
import com.api.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Categoria> findAll (){

        return categoriaRepository.findAll();
    }

    public Categoria findId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria save(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria){
        Categoria newCategoria = findId(categoria.getId());
        updateData(newCategoria, categoria);

        return categoriaRepository.save(newCategoria);

    }

    public void delete(Integer id){
        findId(id);
        try {
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw  new com.api.ordering.service.exception.DataIntegrityViolationException("" +
                    "Não é possível exlcuir uma Categoria que possui Produto.");
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
    public Page<Categoria> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return categoriaRepository.findAll(pageRequest);
    }

    //Convertendo entidade para DTO.
    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    //Atualizando o campo nome, quando o metodo update for acionado.
    private void updateData (Categoria newCategoria, Categoria categoria){
        newCategoria.setNome(categoria.getNome());
    }

}