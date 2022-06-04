package com.agrotis.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.agrotis.agrotis.controllers.LaboratorioController;
import com.agrotis.agrotis.entities.Laboratorio;
import com.agrotis.agrotis.exceptions.ErroChaveLaboratorio;
import com.agrotis.agrotis.repositories.LaboratorioRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste de unidade para o controller de laboratorios")
public class LaboratorioControllerTest {
  
  @MockBean
  private LaboratorioController controller;

  @MockBean
  private LaboratorioRepository labRepository;

  @Test
  public void deveConterUmaListaDeLaboratorios_QuandoChamarFindAll() {
    LaboratorioController controller = new LaboratorioController(labRepository);
    
    Mockito.when(labRepository.findAll()).thenReturn(new ArrayList<Laboratorio>());

    ResponseEntity<List<Laboratorio>> response = controller.getAll();
    
    assertEquals(response.getBody().size(), 0);
    assertEquals(response.getBody().getClass(), ArrayList.class);
  }

  @Test
  public void deveConterUmaListaDeLaboratoriosComPeloMenosUmQuandoHouver_QuandoChamarFindAll() {
    LaboratorioController controller = new LaboratorioController(labRepository);
    Laboratorio lab = new Laboratorio(null, "Agrotis Teste", null);
    
    Mockito.when(labRepository.findAll()).thenReturn(new ArrayList<Laboratorio>(List.of(lab)));
    
    ResponseEntity<List<Laboratorio>> response = controller.getAll();

    assertEquals(response.getBody().size(), 1);
    assertEquals(response.getBody().getClass(), ArrayList.class);
  }

  @Test
  public void deveRetornarUmConflictQuandoReceberUmLabQueJaEstejaCadastrado_QuandoChamarCreate()
      throws Exception {
    LaboratorioController controller = new LaboratorioController(labRepository);
    Laboratorio lab = new Laboratorio(null, "Agrotis Teste", null);
    
    Mockito.when(labRepository.findByName("Agrotis Teste"))
      .thenReturn(new ArrayList<Laboratorio>(List.of(lab)));
    
    final ResponseEntity<Laboratorio> response = controller.create(lab);

    assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
  }

  @Test
  public void deveLancarUmErroAoTentarCadastrarComNomeNull_QuandoChamarCreate() {
    LaboratorioController controller = new LaboratorioController(labRepository);
    Laboratorio lab = new Laboratorio(null, null, null);

    assertThrows(ErroChaveLaboratorio.class, () -> controller.create(lab));
  }

}
