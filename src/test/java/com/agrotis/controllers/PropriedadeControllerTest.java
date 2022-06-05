package com.agrotis.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.agrotis.agrotis.controllers.PropriedadeController;
import com.agrotis.agrotis.entities.Propriedade;
import com.agrotis.agrotis.exceptions.ErroChavePropriedade;
import com.agrotis.agrotis.exceptions.ErroPropriedadeNaoEncontrada;
import com.agrotis.agrotis.repositories.PropriedadeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste de unidade para o controller de propriedades")
public class PropriedadeControllerTest {

  @MockBean
  private PropriedadeController controller;

  @MockBean
  private PropriedadeRepository propriedadeRepository;
  
  @Test
  public void deveConterUmaListaDePropriedades_QuandoChamarFindAll() {
    PropriedadeController controller = new PropriedadeController(propriedadeRepository);
    
    Mockito.when(propriedadeRepository.findAll()).thenReturn(new ArrayList<Propriedade>());

    ResponseEntity<List<Propriedade>> response = controller.getAll();
    
    assertEquals(response.getBody().size(), 0);
    assertEquals(response.getBody().getClass(), ArrayList.class);
  }

  @Test
  public void deveConterUmaListaDePropriedadesComPeloMenosUmQuandoHouver_QuandoChamarFindAll() {
    PropriedadeController controller = new PropriedadeController(propriedadeRepository);
    Propriedade prop = new Propriedade(null, "Prop Teste", "CNPJ test", null);
    
    Mockito.when(propriedadeRepository.findAll())
      .thenReturn(new ArrayList<Propriedade>(List.of(prop)));
    
    ResponseEntity<List<Propriedade>> response = controller.getAll();

    assertEquals(response.getBody().size(), 1);
    assertEquals(response.getBody().getClass(), ArrayList.class);
  }

  @Test
  public void deveRetornarUmConflictQuandoReceberUmLabQueJaEstejaCadastrado_QuandoChamarCreate()
      throws Exception {
    PropriedadeController controller = new PropriedadeController(propriedadeRepository);
    Propriedade prop = new Propriedade(null, "Agrotis Teste", "CNPJ teste", null);
    
    Mockito.when(propriedadeRepository.findByName("Agrotis Teste"))
      .thenReturn(new ArrayList<Propriedade>(List.of(prop)));
    
    final ResponseEntity<Propriedade> response = controller.create(prop);

    assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
  }

  @Test
  public void deveLancarUmErroAoTentarCadastrarComNomeNull_QuandoChamarCreate() {
    PropriedadeController controller = new PropriedadeController(propriedadeRepository);
    Propriedade lab = new Propriedade(null, null,"CNPJ teste", null);

    assertThrows(ErroChavePropriedade.class, () -> controller.create(lab));
  }

  @Test
  public void deveLancarUmErroAoTentarCadastrarComCnpjNull_QuandoChamarCreate() {
    PropriedadeController controller = new PropriedadeController(propriedadeRepository);
    Propriedade lab = new Propriedade(null, "Agrotis teste", null, null);

    assertThrows(ErroChavePropriedade.class, () -> controller.create(lab));
  }

  @Test
  public void deveRetornarErroAoProcurarUmaPropriedadeQueNaoExiste_QuandoChamarFindOneByName()
      throws Exception {
    PropriedadeController controller = new PropriedadeController(propriedadeRepository);

    Mockito.when(propriedadeRepository.findOneByName("")).thenReturn(Optional.empty());

    assertThrows(ErroPropriedadeNaoEncontrada.class, () -> controller.getOneByName(""));
  }
}
