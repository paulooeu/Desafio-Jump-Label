package com.paulo.github.dto;

import com.paulo.github.Model.Repositorio;

public class ResponseDTO {
    Repositorio[] name;

    public Repositorio[] getName() {
        return name;
    }

    public void setName(Repositorio[] name) {
        this.name = name;
    }
}
