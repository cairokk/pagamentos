package com.unifor.pagamentos.controller;


import com.unifor.pagamentos.DTO.DadosPagamento;
import com.unifor.pagamentos.Exception.PagamentoExceptions;
import com.unifor.pagamentos.factory.PagamentoContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamentos", description = "Endpoints para processar pagamentos")
public class PagamentoController {

    @Operation(summary = "Processar um pagamento")
    @PostMapping
    public String processar(@RequestBody DadosPagamento dados) throws PagamentoExceptions {
        new PagamentoContext().processar(dados);
        return "Pagamento processado com sucesso";
    }
}
