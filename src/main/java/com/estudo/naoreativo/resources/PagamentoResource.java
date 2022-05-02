package com.estudo.naoreativo.resources;

import com.estudo.naoreativo.entities.Pagamento;
import com.estudo.naoreativo.repositories.BancoEmMemoria;
import com.estudo.naoreativo.repositories.PagamentoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pagamentos")
@RequiredArgsConstructor
@Slf4j
public class PagamentoResource {

    private final PagamentoRepository pagamentoRepository;

    @PostMapping
    public ResponseEntity<Pagamento> criaPagamento(@RequestBody final NovoPagamento novo) {
        Pagamento pagamento = this.pagamentoRepository.criaPagamento(novo.getUsuarioId());
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Pagamento>> buscaTodosPorId(@RequestParam String ids) {
        final List<String> _ids = Arrays.asList(ids.split(","));
        log.info("Tamanho lista pagamento {}", _ids.size());

        List<Pagamento> pagamentos = _ids.stream().map(id -> this.pagamentoRepository.pegaPagamento(id)).collect(Collectors.toList());
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/ids")
    public ResponseEntity<String> pegaIds() {
        String _ids = String.join(",", BancoEmMemoria.BANCO_DADOS.keySet());
        return ResponseEntity.ok(_ids);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NovoPagamento {
        private String usuarioId;
    }

}
