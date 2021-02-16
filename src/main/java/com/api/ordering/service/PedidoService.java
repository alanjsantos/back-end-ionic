package com.api.ordering.service;

import com.api.ordering.model.ItemPedido;
import com.api.ordering.model.PagamentoComBoleto;
import com.api.ordering.model.Pedido;
import com.api.ordering.model.enums.EstadoPagamento;
import com.api.ordering.repository.ItemPedidoRepository;
import com.api.ordering.repository.PagamentoRepository;
import com.api.ordering.repository.PedidoRepository;
import com.api.ordering.repository.ProdutoRepository;
import com.api.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public Pedido buscarPorId(Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Obejto não encontrado Id: " + id + "Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido save(Pedido pedido){
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        //Testando o tipo de pagamento
        if (pedido.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamento(pagto, pedido.getInstante());
        }

        //salvando pedido
        pedido = pedidoRepository.save(pedido);

        ///salvando pagamento
        pagamentoRepository.save(pedido.getPagamento());

        //salvando os itemPedidos
        for (ItemPedido ip : pedido.getItens()){
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.buscarPorId(ip.getProduto().getId()).getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;


    }

}
