package br.com.pedro.desafioitau.transacao;

import br.com.pedro.desafioitau.estatistica.EstatisticaResponse;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransacaoRepository {

    private final List<TransacaoRequest> transacoes = new ArrayList<>();

    public void add(TransacaoRequest transacaoRequestDTO){
        transacoes.add(transacaoRequestDTO);
    }

    public List<TransacaoRequest> listarTransacoes(){
        return transacoes.stream()
                .toList();
    }

    public void limpar() {
        transacoes.clear();
    }

    public EstatisticaResponse calcularEstatistica(){
        List<TransacaoRequest> transacoes = listarTransacoes();

        if (transacoes.isEmpty()) {
            return new EstatisticaResponse(0L, 0.0, 0.0, 0.0, 0.0);
        }

        List<TransacaoRequest> valoresFiltrados = listarTransacoes().stream()
                .filter(t -> t.dataHora().isAfter(OffsetDateTime.now().minusMinutes(1)))
                .collect(Collectors.toList());

        DoubleSummaryStatistics statistics = valoresFiltrados.stream()
                .mapToDouble(transacao -> transacao.valor().doubleValue())
                .summaryStatistics();

        return new EstatisticaResponse(statistics.getCount(), statistics.getSum(), statistics.getAverage(), statistics.getMin(), statistics.getMax());

    }
}
