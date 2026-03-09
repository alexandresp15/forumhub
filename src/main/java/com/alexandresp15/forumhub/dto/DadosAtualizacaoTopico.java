package com.alexandresp15.forumhub.dto;

import com.alexandresp15.forumhub.domain.topico.StatusTopico;

public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        String curso,
        StatusTopico status
) {
}