package com.alexandresp15.forumhub.dto;

public record DadosCadastroTopico(
        String titulo,
        String mensagem,
        String autor,
        String curso
) {
}