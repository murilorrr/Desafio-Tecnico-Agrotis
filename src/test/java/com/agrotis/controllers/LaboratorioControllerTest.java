package com.agrotis.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

import com.agrotis.agrotis.controllers.LaboratorioController;
import com.agrotis.agrotis.entities.Laboratorio;
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
    
    assertEquals(controller.getAll().size(), 0);
    assertEquals(controller.getAll().getClass(), ArrayList.class);
  }

  @Test
  public void deveConterUmaListaDeLaboratoriosComPeloMenosUmQuandoHouver_QuandoChamarFindAll() {
    LaboratorioController controller = new LaboratorioController(labRepository);
    Laboratorio lab = new Laboratorio(null, "Agrotis Teste", null);
    
    Mockito.when(labRepository.findAll()).thenReturn(new ArrayList<Laboratorio>(List.of(lab)));
    
    assertEquals(controller.getAll().size(), 1);
    assertEquals(controller.getAll().getClass(), ArrayList.class);
  }

  @Test
  public void deveRetornarUmConflictQuandoReceberUmLabQueJaEstejaCadastrado_QuandoChamarFindAll()
      throws Exception {
    LaboratorioController controller = new LaboratorioController(labRepository);
    Laboratorio lab = new Laboratorio(null, "Agrotis Teste", null);
    
    Mockito.when(labRepository.findByName("Agrotis Teste"))
      .thenReturn(new ArrayList<Laboratorio>(List.of(lab)));
    
    final ResponseEntity<Laboratorio> response = controller.create(lab);

    assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
  }

}
