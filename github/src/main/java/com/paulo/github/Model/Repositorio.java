package com.paulo.github.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "repositorio")
public class Repositorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome é obrigatorio")
    private String name;

    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Repositorio() {

    }

    public Repositorio(Long id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public Repositorio(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
