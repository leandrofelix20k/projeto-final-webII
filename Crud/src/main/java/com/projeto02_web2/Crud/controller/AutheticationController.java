package com.projeto02_web2.Crud.controller;

import com.projeto02_web2.Crud.Configurations.TokenService;
import com.projeto02_web2.Crud.DTO.AuthenticationDTO;
import com.projeto02_web2.Crud.DTO.LoginDTO;
import com.projeto02_web2.Crud.DTO.RegisterDTO;
import com.projeto02_web2.Crud.Service.AuthorizationService;
import com.projeto02_web2.Crud.model.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AutheticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationService service;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<Object> validacao(@RequestBody @Valid AuthenticationDTO credeciais) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(credeciais.login(), credeciais.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.criarToken((UsuarioEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO credeciais, UriComponentsBuilder uriBuilder) {
        if(service.cadastrarUsuario(credeciais) == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.created(uriBuilder.path("/auth/login").build().toUri()).build();
    }
}
