package com.paulo.github.service;

import com.paulo.github.Model.Repositorio;
import com.paulo.github.repository.RepositorioRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositorioServiceTest {

    @Mock
    private RespositorioService service;

    @Autowired
    private RepositorioRepository repositorioRepository;


    @Test
    public void testCadastrarRepositorio() {
        Repositorio repositorio = criarRepositorio();
        Mockito.when(service.cadastrarRepositorio(repositorio)).thenReturn(repositorio);

    }

    @Test
    public void testAtualizarRepositorio() {

        Repositorio repositorio = criarRepositorio();
        Repositorio repositorioResponseCadastro = this.repositorioRepository.save(repositorio);
        repositorioResponseCadastro.setName(Mockito.anyString());
        repositorioResponseCadastro.setLanguage(Mockito.anyString());
        Repositorio repositorioResponseUpdade = this.repositorioRepository.save(repositorioResponseCadastro);

        Assert.assertEquals(repositorioResponseCadastro.getId(), repositorioResponseUpdade.getId());

    }

    @Test
    public void testDeletarRepositorio() {

        Repositorio repositorio = criarRepositorio();
        Repositorio repositorioResponseCadastro = this.repositorioRepository.save(repositorio);
        this.repositorioRepository.delete(repositorioResponseCadastro);
        Mockito.when(repositorioRepository.findById(1L)).thenReturn(null);

    }

    @Test
    public void testListarRepositorio() {

        List<Repositorio> repositorios = repositorios();
        for (Repositorio repositorio : repositorios) {
            this.repositorioRepository.save(repositorio);
        }
        Assert.assertEquals(repositorioRepository.findAll(),repositorios);


    }

    private List<Repositorio> repositorios() {
        List<Repositorio> lista = new ArrayList<>();
        Repositorio repositorio1 = new Repositorio("repo1", "java");
        Repositorio repositorio2 = new Repositorio("repo2", "java");
        lista.add(repositorio1);
        lista.add(repositorio2);
        return lista;
    }

    private Repositorio criarRepositorio() {
        Repositorio repositorio = new Repositorio(Mockito.anyString(), Mockito.anyString());
        return repositorio;
    }

}
