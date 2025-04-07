package br.com.pedro.desafioitau.estatistica;

public record EstatisticaResponse(
        long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {
}

