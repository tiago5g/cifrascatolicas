package com.tiagorafaell.cifrascatolicas.infrastructure.auth;

import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthRequest;
import com.tiagorafaell.cifrascatolicas.application.auth.dto.AuthResponse;
import com.tiagorafaell.cifrascatolicas.application.auth.CadastrarUsuarioUseCase;
import com.tiagorafaell.cifrascatolicas.application.auth.LoginUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Realiza o cadastro de usuário")
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        return cadastrarUsuario.execute(request);
    }

    @Operation(summary = "Realiza login do usuário")
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return loginUsuario.execute(request);
    }
}
