package com.tiagorafaell.cifrascatolicas.infrastructure.musica;

import com.tiagorafaell.cifrascatolicas.domain.musica.Musica;
import com.tiagorafaell.cifrascatolicas.domain.musica.MusicaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaJpaRepository extends JpaRepository<Musica, Long>, MusicaRepository {

}