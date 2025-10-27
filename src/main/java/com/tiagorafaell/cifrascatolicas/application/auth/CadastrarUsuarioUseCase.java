package com.tiagorafaell.cifrascatolicas.application.auth;

import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthRequest;
import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthResponse;
import com.tiagorafaell.cifrascatolicas.domain.usuario.Usuario;
import com.tiagorafaell.cifrascatolicas.domain.usuario.UsuarioRepository;
import com.tiagorafaell.cifrascatolicas.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CadastrarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public CadastrarUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse execute(AuthRequest request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuarioRepository.save(usuario);
        String token = jwtService.gerarToken(usuario.getUsername());
        return new AuthResponse(token);
    }
}
