package br.com.pedro.desafioitau.transacao.exceptions;

public class TransacaoInvalidaException extends RuntimeException{
    public TransacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
