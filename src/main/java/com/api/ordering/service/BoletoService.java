package com.api.ordering.service;

import com.api.ordering.model.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    // Metodo que coloca a data de vencimento como sendo 7 dias depois dessa data.
    public void preencherPagamento(PagamentoComBoleto pagt, Date data){
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagt.setDataPagamento(cal.getTime());
    }
}
