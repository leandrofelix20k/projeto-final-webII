package com.projeto02_web2.Crud.Service;

import com.projeto02_web2.Crud.DTO.AuthenticationDTO;
import com.projeto02_web2.Crud.DTO.RegisterDTO;
import com.projeto02_web2.Crud.enums.UserRoles;
import com.projeto02_web2.Crud.model.UsuarioEntity;
import com.projeto02_web2.Crud.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    public UsuarioEntity cadastrarUsuario(RegisterDTO credeciais) {
        if(usuarioRepository.findByLogin(credeciais.login()) != null){
            return null;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(credeciais.password());
        UsuarioEntity usuario = new UsuarioEntity(credeciais.login(), encryptedPassword, credeciais.role());

        return usuarioRepository.save(usuario);
    }


}
