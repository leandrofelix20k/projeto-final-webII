package com.projeto02_web2.Crud.DTO;

import com.projeto02_web2.Crud.enums.UserRoles;
import org.apache.catalina.User;

public record RegisterDTO(
        String login,
        String password,
        UserRoles role) {

}
