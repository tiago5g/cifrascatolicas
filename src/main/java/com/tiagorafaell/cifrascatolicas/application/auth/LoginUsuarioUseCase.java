package com.tiagorafaell.cifrascatolicas.application.auth;

import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthRequest;
import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthResponse;
import com.tiagorafaell.cifrascatolicas.domain.usuario.UsuarioRepository;
import com.tiagorafaell.cifrascatolicas.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse execute(AuthRequest request) {
        var usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.gerarToken(usuario.getUsername());
        return new AuthResponse(token);
    }
}
