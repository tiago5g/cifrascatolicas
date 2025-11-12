package com.tiagorafaell.cifrascatolicas.application.musica;
import com.tiagorafaell.cifrascatolicas.application.musica.dto.CadastrarMusicaDTO;
import com.tiagorafaell.cifrascatolicas.application.musica.dto.MusicaResponseDTO;
import com.tiagorafaell.cifrascatolicas.domain.musica.Musica;
import com.tiagorafaell.cifrascatolicas.domain.musica.MusicaRepository;
import com.tiagorafaell.cifrascatolicas.domain.musica.MusicaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarMusicaUseCaseTest {

    @Mock
    private MusicaRepository musicaRepository;

    @Mock
    private MusicaService musicaService;

    @InjectMocks
    private CadastrarMusicaUseCase cadastrarMusicaUseCase;

    @Test
    void deveCadastrarMusicaComSucesso() {
        // Arrange: cria o DTO
        CadastrarMusicaDTO dto = new CadastrarMusicaDTO();
        dto.titulo = "Oceans";
        dto.artista = "Hillsong";
        dto.album = "Zion";
        dto.tom = "D";
        dto.letra = "Spirit lead me...";
        dto.cifra = "D G Bm A";

        // Entidade que o repository deveria salvar
        Musica musicaSalva = new Musica();
        musicaSalva.setTitulo(dto.titulo);
        musicaSalva.setArtista(dto.artista);

        when(musicaRepository.save(any(Musica.class))).thenReturn(musicaSalva);

        // Act: executa o caso de uso
        MusicaResponseDTO resultado = cadastrarMusicaUseCase.executar(dto);

        assertNotNull(resultado);
        assertEquals("Oceans", resultado.titulo);
        assertEquals("Hillsong", resultado.artista);
        verify(musicaRepository, times(1)).save(any(Musica.class));
    }

    @Test
    void deveLancarExcecaoQuandoTituloNaoInformado() {
        // Arrange: cria o DTO sem título
        CadastrarMusicaDTO dto = new CadastrarMusicaDTO();
        dto.titulo = null; // título ausente
        dto.artista = "Hillsong";
        dto.album = "Zion";
        dto.tom = "D";
        dto.letra = "Spirit lead me...";
        dto.cifra = "D G Bm A";

        // Configura o mock do MusicaService para lançar IllegalArgumentException
        doThrow(new IllegalArgumentException("O Título é obrigatório"))
                .when(musicaService).validate(any(Musica.class));

        // Act + Assert: espera exceção ao executar
        assertThrows(IllegalArgumentException.class, () -> {
            cadastrarMusicaUseCase.executar(dto);
        });

        // Verifica que o repository não foi chamado
        verify(musicaRepository, never()).save(any(Musica.class));
        verify(musicaService, times(1)).validate(any(Musica.class));
    }

    @Test
    void deveLancarExcecaoQuandoTomInvalido() {
        // Arrange: cria o DTO com tom inválido
        CadastrarMusicaDTO dto = new CadastrarMusicaDTO();
        dto.titulo = "Oceans";
        dto.artista = "Hillsong";
        dto.album = "Zion";
        dto.tom = "H#"; // tom inválido
        dto.letra = "Spirit lead me...";
        dto.cifra = "D G Bm A";

        // Configura o mock do MusicaService para lançar IllegalArgumentException
        doThrow(new IllegalArgumentException("Formato de Tom Inválido"))
                .when(musicaService).validate(any(Musica.class));

        // Act + Assert: espera exceção ao executar
        assertThrows(IllegalArgumentException.class, () -> {
            cadastrarMusicaUseCase.executar(dto);
        });

        // Verifica que o repository não foi chamado
        verify(musicaRepository, never()).save(any(Musica.class));
        verify(musicaService, times(1)).validate(any(Musica.class));
    }


}
