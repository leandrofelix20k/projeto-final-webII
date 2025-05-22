package com.projeto02_web2.Crud.dataInitialazer;

import com.projeto02_web2.Crud.enums.UserRoles;
import com.projeto02_web2.Crud.model.UsuarioEntity;
import com.projeto02_web2.Crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class primeiroUsuario implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0){
            UsuarioEntity admin = new UsuarioEntity();
            String encryptedPassword = new BCryptPasswordEncoder().encode("admin");
            admin.setLogin("admin");
            admin.setPassword(encryptedPassword);
            admin.setRole(UserRoles.ADMIN);
            usuarioRepository.save(admin);
        }
    }
}
