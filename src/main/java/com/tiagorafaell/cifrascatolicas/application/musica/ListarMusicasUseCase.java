package com.tiagorafaell.cifrascatolicas.application.musica;
import com.tiagorafaell.cifrascatolicas.domain.musica.MusicaRepository;
import com.tiagorafaell.cifrascatolicas.application.musica.dto.MusicaResponseDTO;
import com.tiagorafaell.cifrascatolicas.application.musica.mapper.MusicaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarMusicasUseCase {

    private final MusicaRepository musicaRepository;

    public ListarMusicasUseCase(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    public List<MusicaResponseDTO> executar() {
        return musicaRepository.findAll()
                .stream()
                .map(MusicaMapper::toResponse)
                .toList();
    }
}
