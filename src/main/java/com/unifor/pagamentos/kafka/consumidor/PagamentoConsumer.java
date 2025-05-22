package com.unifor.pagamentos.kafka.consumidor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifor.pagamentos.DTO.DadosPagamento;
import com.unifor.pagamentos.DTO.PagamentoMensagem;
import com.unifor.pagamentos.factory.PagamentoContext;
import com.unifor.pagamentos.kafka.publicador.PagamentoProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagamentoConsumer {

    @Autowired
    private PagamentoProducer producer;

    @Autowired
    private  ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "pagamentos-pendentes", groupId = "grupo-pagamento")
    public void receberMensagem(String mensagemJson) {
        try {
            PagamentoMensagem mensagem = objectMapper.readValue(mensagemJson, PagamentoMensagem.class);

            boolean aprovado = processar(mensagem.getDadosPagamento());

            String status = aprovado ? "PAGO" : "CANCELADO";
            producer.enviarStatusPagamento(mensagem.getPedidoId(), status);

        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem Kafka: " + e.getMessage());
        }
    }

    private boolean processar(DadosPagamento dados) {
        try {
            new PagamentoContext().processar(dados);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

