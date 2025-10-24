package com.tiagorafaell.cifrascatolicas.domain.musica;

import java.util.List;

public interface MusicaRepository {
    Musica save(Musica music);
    List<Musica> findAll();
}