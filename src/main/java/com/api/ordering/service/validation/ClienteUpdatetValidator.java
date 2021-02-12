package com.api.ordering.service.validation;

import com.api.ordering.controller.exception.FieldMessage;
import com.api.ordering.dto.ClienteDTO;
import com.api.ordering.dto.ClienteNewDTO;
import com.api.ordering.model.Cliente;
import com.api.ordering.model.enums.TipoCliente;
import com.api.ordering.repository.ClienteRepository;
import com.api.ordering.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdatetValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        //Capturando o id da URI.
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId =  Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        //Buscando email existente no banco de dados.
        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());

        //testando o email: Comparando o email existente com o email existente de um outro usuario.
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}