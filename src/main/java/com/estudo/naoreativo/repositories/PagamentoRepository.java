package com.estudo.naoreativo.repositories;

import com.estudo.naoreativo.entities.Pagamento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PagamentoRepository {

    private final BancoDados bancoDados;

    public Pagamento criaPagamento(final String usuarioId) {

        final Pagamento pagamento = Pagamento.builder()
                .id(UUID.randomUUID().toString())
                .usuarioId(usuarioId)
                .status(Pagamento.PagamentoStatus.PENDENTE)
                .build();

        return this.bancoDados.salvar(usuarioId, pagamento);
    }

    public Pagamento pegaPagamento(final String usuarioId) {
        Optional<Pagamento> optPagamento = this.bancoDados.pegar(usuarioId, Pagamento.class);

        return optPagamento.orElse(null);
    }
}
