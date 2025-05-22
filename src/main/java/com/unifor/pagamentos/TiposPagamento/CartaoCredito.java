package com.unifor.pagamentos.TiposPagamento;

import com.unifor.pagamentos.DTO.DadosPagamento;
import com.unifor.pagamentos.Exception.PagamentoExceptions;
import com.unifor.pagamentos.interfaces.estrategiaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoCredito implements estrategiaPagamento {

    private String numeroCartao;
    private String nome;
    private String ccv;
    private String dataValidade;

    @Override
    public void pagar(DadosPagamento dados) throws PagamentoExceptions {

        validarDados(dados);

        System.out.println("Iniciando processamento do pagamento com cartão de crédito...");

        // Simula uma aprovação com 80% de chance
        double chance = Math.random();
        if (chance <= 0.8) {
            System.out.println("✅ Pagamento aprovado no valor de: R$" + dados.getValor());
        } else {
            System.out.println("❌ Pagamento recusado. Tente novamente.");
        }

    }


    @Override
    public String getDetalhesPagamento(){

        return "Cartão de crédito: " + mascaraNumeroCartao(numeroCartao) + ", Nome: " +nome;
    };

    private void validarDados(DadosPagamento dados) throws PagamentoExceptions {
        if (dados.getValor() <= 0) {
            throw new PagamentoExceptions("O valor do pagamento deve ser maior que zero.");
        }

        if (dados.getNumeroCartao() == null || dados.getNumeroCartao().isBlank()) {
            throw new PagamentoExceptions("Número do cartão é obrigatório.");
        }

        if (dados.getNomeTitular() == null || dados.getNomeTitular().isBlank()) {
            throw new PagamentoExceptions("Nome do titular é obrigatório.");
        }

        if (dados.getValidade() == null || dados.getValidade().isBlank()) {
            throw new PagamentoExceptions("Data de validade do cartão é obrigatória.");
        }

        if (dados.getCvv() == null || dados.getCvv().isBlank()) {
            throw new PagamentoExceptions("CVV do cartão é obrigatório.");
        }
    }
    private String mascaraNumeroCartao (String numeroCartao){
        return "**** **** ****" + numeroCartao.substring(12);
    }
}
