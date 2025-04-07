package br.com.pedro.desafioitau.transacao;

import br.com.pedro.desafioitau.transacao.exceptions.TransacaoInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class TransacaoHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Map<String, String>> erros = e.getFieldErrors()
                .stream()
                .map(
                        fieldError -> Map.of(
                                "message", fieldError.getDefaultMessage(),
                                "field", fieldError.getField()
                        )).toList();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Validation Error"
        );
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("errors", erros);
        return problemDetail;
    }

    @ExceptionHandler(TransacaoInvalidaException.class)
    public ProblemDetail handleTransacaoInvalidaException(TransacaoInvalidaException e) {
        String details = e.getMessage();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Error"
        );
        problemDetail.setTitle("Internal Error");
        problemDetail.setDetail(details);

        return problemDetail;
    }

}
