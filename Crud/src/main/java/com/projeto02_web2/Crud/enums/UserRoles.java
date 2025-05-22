package com.projeto02_web2.Crud.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRoles (String role){
        this.role = role;
    }

}
