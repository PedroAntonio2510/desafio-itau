package br.com.pedro.desafioitau.transacao;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoRequest(
        @NotNull(message = "O valor nao pode ser vazio")
        BigDecimal valor,

        @NotNull(message = "Voce deve especificar uma data e hora para transacao")
        OffsetDateTime dataHora
) {
}
