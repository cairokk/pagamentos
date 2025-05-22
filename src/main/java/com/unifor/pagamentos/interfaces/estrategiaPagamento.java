package com.unifor.pagamentos.interfaces;

import com.unifor.pagamentos.DTO.DadosPagamento;
import com.unifor.pagamentos.Exception.PagamentoExceptions;

public interface estrategiaPagamento {

    void pagar(DadosPagamento dados) throws PagamentoExceptions;

    String getDetalhesPagamento();

}
