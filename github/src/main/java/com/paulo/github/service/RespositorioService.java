package com.paulo.github.service;

import com.paulo.github.Model.Repositorio;
import com.paulo.github.dto.ResponseDTO;
import com.paulo.github.erro.ResourceNotAcceptableException;
import com.paulo.github.repository.RepositorioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class RespositorioService {

    private final String MSG_REPOSITORIO_INEXISTENTE = "Repositorio não existe!";


    private RepositorioRepository repositorioRepository;

    public RespositorioService(RepositorioRepository repositorioRepository) {
        this.repositorioRepository = repositorioRepository;
    }

    @Autowired
    RestTemplate restTemplate;

    public List<Repositorio> listarRepositorios() {
        buscarGitHub();
        return repositorioRepository.findAll();
    }

    public Repositorio cadastrarRepositorio(Repositorio repositorio) {
        Repositorio repositorioRetorno = repositorioRepository.save(repositorio);
        return repositorioRetorno;
    }

    public Repositorio atualizarRepositorio(Repositorio repositorio) {
        if (!repositorioRepository.existsById(repositorio.getId())) {
            throw new ResourceNotAcceptableException(MSG_REPOSITORIO_INEXISTENTE);
        }
        Repositorio retornoRepositorio = repositorioRepository.save(repositorio);
        return retornoRepositorio;
    }

    public Boolean apagarRepositorio(Long id) {
        Boolean retorno = Boolean.FALSE;
        if (repositorioRepository.existsById(id)) {
            repositorioRepository.deleteById(id);
            retorno = Boolean.TRUE;
        }
        return retorno;
    }

    public List<Repositorio> importarRepositorio(String nome) {
        List<Repositorio> repositorios = Arrays.asList(buscarGitHub());
        for (Repositorio repositorio : repositorios) {
            if (!repositorioRepository.existsById(repositorio.getId())) {
                cadastrarRepositorio(repositorio);
            }
        }
        return repositorioRepository.findAll();
    }


    private Repositorio[] buscarGitHub() {
        String url = "https://api.github.com/users/paulooeu/repos";

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Repositorio[]> response = restTemplate.exchange(url,
                HttpMethod.GET,
                entity,
                Repositorio[].class,
                "json"
        );
        return response.getBody();

    }
}
