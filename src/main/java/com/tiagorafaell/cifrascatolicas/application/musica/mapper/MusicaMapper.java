package com.tiagorafaell.cifrascatolicas.application.musica.mapper;

import com.tiagorafaell.cifrascatolicas.application.musica.dto.CadastrarMusicaDTO;
import com.tiagorafaell.cifrascatolicas.application.musica.dto.MusicaResponseDTO;
import com.tiagorafaell.cifrascatolicas.domain.musica.Musica;

public class MusicaMapper {

    public static Musica toEntity(CadastrarMusicaDTO dto) {
        Musica musica = new Musica();
        musica.setTitulo(dto.titulo);
        musica.setArtista(dto.artista);
        musica.setTom(dto.tom);
        musica.setAlbum(dto.album);
        musica.setLetra(dto.letra);
        musica.setCifra(dto.cifra);
        return musica;
    }

    public static MusicaResponseDTO toResponse(Musica musica) {
        MusicaResponseDTO dto = new MusicaResponseDTO();
        dto.id = musica.getId();
        dto.titulo = musica.getTitulo();
        dto.artista = musica.getArtista();
        dto.tom = musica.getTom();
        dto.album = musica.getAlbum();
        dto.letra = musica.getLetra();
        dto.cifra = musica.getCifra();
        return dto;
    }
}
