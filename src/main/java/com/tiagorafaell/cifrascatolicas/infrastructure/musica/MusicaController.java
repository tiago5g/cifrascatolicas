package com.tiagorafaell.cifrascatolicas.infrastructure.musica;

import com.tiagorafaell.cifrascatolicas.application.musica.CadastrarMusicaUseCase;
import com.tiagorafaell.cifrascatolicas.application.musica.ListarMusicasUseCase;
import com.tiagorafaell.cifrascatolicas.application.musica.dto.CadastrarMusicaDTO;
import com.tiagorafaell.cifrascatolicas.application.musica.dto.MusicaResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final CadastrarMusicaUseCase cadastrarMusicaUseCase;
    private final ListarMusicasUseCase listarMusicasUseCase;

    public MusicaController(CadastrarMusicaUseCase cadastrarMusicaUseCase, ListarMusicasUseCase listarMusicasUseCase) {
        this.cadastrarMusicaUseCase = cadastrarMusicaUseCase;
        this.listarMusicasUseCase = listarMusicasUseCase;
    }

    @PostMapping
    public ResponseEntity<MusicaResponseDTO> cadastrar(@RequestBody CadastrarMusicaDTO dto) {
        MusicaResponseDTO response = cadastrarMusicaUseCase.executar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MusicaResponseDTO>> listar() {
        List<MusicaResponseDTO> musicas = listarMusicasUseCase.executar();
        return ResponseEntity.ok(musicas);
    }
}
