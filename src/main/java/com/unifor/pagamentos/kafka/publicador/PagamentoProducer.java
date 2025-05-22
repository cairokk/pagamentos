package com.unifor.pagamentos.kafka.publicador;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PagamentoProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void enviarStatusPagamento(Long idPedido, String status) {
        Map<String, Object> mensagem = Map.of(
                "pedidoId", idPedido,
                "status", status
        );

        kafkaTemplate.send("pagamentos-processados", mensagem);
        System.out.println("Mensagem enviada para pagamentos-processados: " + mensagem);
    }
}

