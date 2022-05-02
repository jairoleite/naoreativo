package com.estudo.naoreativo.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pagamento {

    String id;
    String usuarioId;
    PagamentoStatus status;

    public enum PagamentoStatus {
        PENDENTE, APROVADO
    }
}
