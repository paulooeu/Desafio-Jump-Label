package com.paulo.github.controller;

import com.paulo.github.Model.Repositorio;
import com.paulo.github.service.RespositorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/repositorios")
@Api(value = "Repositorio", description = "REST API para Repositorio", tags = { "Repositorio" })
public class RepositorioController {

    @Autowired
    private RespositorioService service;

    @ApiOperation(value = "Listar todos os repositorios")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Repositorio>> listaRepositorio() {
        return new ResponseEntity<List<Repositorio>>(service.listarRepositorios(), HttpStatus.OK);
    }

    @ApiOperation(value = "Cria um Repositorio")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cadastrarRepositorio(@RequestBody(required = true) @Valid Repositorio repositorio) {
        return new ResponseEntity<Repositorio>(service.cadastrarRepositorio(repositorio), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Atualizar o Repositorio")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarRepositorio(@RequestBody(required = true) @Valid Repositorio repositorio) {
        return new ResponseEntity<Repositorio>(service.atualizarRepositorio(repositorio), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar o Repositorio")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> apagarRepositorio(@PathVariable Long id) {
        return new ResponseEntity<Boolean>(service.apagarRepositorio(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Imporatar Repositorios do github")
    @GetMapping("/{nome}")
    public ResponseEntity<List<Repositorio>>ImportarRepositorio(@PathVariable String nome) {
        return new ResponseEntity<List<Repositorio>>(service.importarRepositorio(nome), HttpStatus.OK);
    }
}
