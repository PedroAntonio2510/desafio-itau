package br.com.pedro.desafioitau.transacao;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid TransacaoRequest transacaoRequestDTO) {
        transacaoService.create(transacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        transacaoService.deletarTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<TransacaoRequest>> list() {
        List<TransacaoRequest> transacoes = transacaoService.listarTransacoes();
        return ResponseEntity.ok(transacoes);
    }
}
