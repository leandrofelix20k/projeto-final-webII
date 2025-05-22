package com.projeto02_web2.Crud.enums;

import lombok.Getter;

@Getter
public enum Genero {
    MASCULINO("masculino"),
    FEMININO("feminino"),
    OUTRO("outro");

    private String genero;

    Genero (String genero){
        this.genero = genero;
    }
}
