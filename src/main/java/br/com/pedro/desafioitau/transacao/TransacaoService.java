package br.com.pedro.desafioitau.transacao;

import br.com.pedro.desafioitau.transacao.exceptions.TransacaoInvalidaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public void create(TransacaoRequest request) {
        validarTransacao(request);

        transacaoRepository.add(request);
    }

    public List<TransacaoRequest> listarTransacoes() {
        return transacaoRepository.listarTransacoes();
    }

    public void deletarTransacoes() {
        transacaoRepository.limpar();
    }

    private void validarTransacao(TransacaoRequest request) {
        if (request.dataHora().isAfter(OffsetDateTime.now())) {
            throw new TransacaoInvalidaException("A transacao nao pode ocorre no futuro");
        }
        if (request.valor().compareTo(BigDecimal.ZERO) < 0) {
            throw new TransacaoInvalidaException("O valor nao pode ser negativo");
        }
    }
}
