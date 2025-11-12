package com.tiagorafaell.cifrascatolicas.application.auth;
import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthRequest;
import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthResponse;
import com.tiagorafaell.cifrascatolicas.domain.usuario.Usuario;
import com.tiagorafaell.cifrascatolicas.domain.usuario.UsuarioRepository;
import com.tiagorafaell.cifrascatolicas.infrastructure.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LoginUsuarioUseCase loginUsuarioUseCase;

    @Test
    void deveAutenticarUsuarioComCredenciaisValidas() {
        // Arrange
        AuthRequest request = new AuthRequest("tiago", "senha123");
        Usuario usuario = new Usuario();
        usuario.setUsername("tiago");
        usuario.setSenha("senhaCriptografada");

        when(usuarioRepository.findByUsername("tiago")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senha123", "senhaCriptografada")).thenReturn(true);
        when(jwtService.gerarToken("tiago")).thenReturn("tokenValido");

        // Act
        AuthResponse response = loginUsuarioUseCase.execute(request);

        // Assert
        assertNotNull(response);
        assertEquals("tokenValido", response.token());
        verify(usuarioRepository, times(1)).findByUsername("tiago");
        verify(passwordEncoder, times(1)).matches("senha123", "senhaCriptografada");
        verify(jwtService, times(1)).gerarToken("tiago");
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        AuthRequest request = new AuthRequest("inexistente", "senha");

        when(usuarioRepository.findByUsername("inexistente")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> loginUsuarioUseCase.execute(request));

        assertEquals("Usuário não encontrado", ex.getMessage());
        verify(usuarioRepository, times(1)).findByUsername("inexistente");
        verifyNoInteractions(passwordEncoder, jwtService);
    }

    @Test
    void deveLancarExcecaoQuandoSenhaInvalida() {
        AuthRequest request = new AuthRequest("tiago", "senhaErrada");
        Usuario usuario = new Usuario();
        usuario.setUsername("tiago");
        usuario.setSenha("senhaCriptografada");

        when(usuarioRepository.findByUsername("tiago")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senhaErrada", "senhaCriptografada")).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> loginUsuarioUseCase.execute(request));

        assertEquals("Senha inválida", ex.getMessage());
        verify(usuarioRepository, times(1)).findByUsername("tiago");
        verify(passwordEncoder, times(1)).matches("senhaErrada", "senhaCriptografada");
        verifyNoInteractions(jwtService);
    }
}

