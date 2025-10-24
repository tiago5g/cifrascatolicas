package com.tiagorafaell.cifrascatolicas.domain.musica;

import org.springframework.stereotype.Service;

@Service
public class MusicaService {

    public void validate(Musica musica) {
        if (musica.getTitulo() == null || musica.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O Título é obrigatório");
        }

        if (musica.getTom() != null && !musica.getTom().matches("[A-G]#?m?b?")) {
            throw new IllegalArgumentException("Formato de Tom Inválido");
        }
    }
}
