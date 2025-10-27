package com.tiagorafaell.cifrascatolicas.infrastructure.auth;

import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthRequest;
import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthResponse;
import com.tiagorafaell.cifrascatolicas.application.auth.CadastrarUsuarioUseCase;
import com.tiagorafaell.cifrascatolicas.application.auth.LoginUsuarioUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CadastrarUsuarioUseCase cadastrarUsuario;
    private final LoginUsuarioUseCase loginUsuario;

    public AuthController(CadastrarUsuarioUseCase cadastrarUsuario, LoginUsuarioUseCase loginUsuario) {
        this.cadastrarUsuario = cadastrarUsuario;
        this.loginUsuario = loginUsuario;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        return cadastrarUsuario.execute(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return loginUsuario.execute(request);
    }
}
