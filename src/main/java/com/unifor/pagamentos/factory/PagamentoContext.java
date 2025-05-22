package com.unifor.pagamentos.factory;

import com.unifor.pagamentos.DTO.DadosPagamento;
import com.unifor.pagamentos.Exception.PagamentoExceptions;
import com.unifor.pagamentos.TiposPagamento.CartaoCredito;
import com.unifor.pagamentos.interfaces.estrategiaPagamento;

public class PagamentoContext {

    public static estrategiaPagamento getStrategy(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "cartao" -> new CartaoCredito();
            // outros tipos aqui
            default -> throw new IllegalArgumentException("Tipo de pagamento inválido: " + tipo);
        };
    }

    public void processar(DadosPagamento dados) throws PagamentoExceptions {
        estrategiaPagamento strategy = getStrategy(dados.getTipo());
        strategy.pagar(dados);
    }
}
