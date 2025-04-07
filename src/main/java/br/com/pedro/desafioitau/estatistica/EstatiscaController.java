package br.com.pedro.desafioitau.estatistica;

import br.com.pedro.desafioitau.transacao.TransacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatiscaController {

    private final TransacaoRepository transacaoRepository;

    public EstatiscaController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping
    public ResponseEntity<EstatisticaResponse> calcularEstatistica() {
        EstatisticaResponse response = transacaoRepository.calcularEstatistica();
        return ResponseEntity.ok(response);
    }
}
