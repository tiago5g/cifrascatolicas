package com.tiagorafaell.cifrascatolicas.application.musica;

import com.tiagorafaell.cifrascatolicas.domain.musica.Musica;
import com.tiagorafaell.cifrascatolicas.domain.musica.MusicaRepository;
import com.tiagorafaell.cifrascatolicas.domain.musica.MusicaService;
import com.tiagorafaell.cifrascatolicas.application.musica.dto.CadastrarMusicaDTO;
import com.tiagorafaell.cifrascatolicas.application.musica.dto.MusicaResponseDTO;
import com.tiagorafaell.cifrascatolicas.application.musica.mapper.MusicaMapper;
import org.springframework.stereotype.Service;

@Service
public class CadastrarMusicaUseCase {

    private final MusicaRepository musicaRepository;
    private final MusicaService musicaService;

    public CadastrarMusicaUseCase(MusicaRepository musicaRepository, MusicaService musicaService) {
        this.musicaRepository = musicaRepository;
        this.musicaService = musicaService;
    }

    public MusicaResponseDTO executar(CadastrarMusicaDTO dto) {
        Musica musica = MusicaMapper.toEntity(dto);
        musicaService.validate(musica);
        Musica saved = musicaRepository.save(musica);
        return MusicaMapper.toResponse(saved);
    }
}