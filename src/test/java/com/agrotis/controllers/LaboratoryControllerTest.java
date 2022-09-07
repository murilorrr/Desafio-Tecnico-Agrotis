package com.agrotis.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.agrotis.agrotis.controllers.LaboratoryController;
import com.agrotis.agrotis.entities.Laboratory;
import com.agrotis.agrotis.exceptions.KeyErrorLaboratory;
import com.agrotis.agrotis.repositories.LaboratoryRepository;

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
public class LaboratoryControllerTest {
  
  @MockBean
  private LaboratoryController controller;

  @MockBean
  private LaboratoryRepository labRepository;

  @Test
  public void deveConterUmaListaDeLaboratorios_QuandoChamarFindAll() {
    LaboratoryController controller = new LaboratoryController(labRepository);
    
    Mockito.when(labRepository.findAll()).thenReturn(new ArrayList<Laboratory>());

    ResponseEntity<List<Laboratory>> response = controller.getAll();
    
    assertEquals(response.getBody().size(), 0);
    assertEquals(response.getBody().getClass(), ArrayList.class);
  }

  @Test
  public void deveConterUmaListaDeLaboratoriosComPeloMenosUmQuandoHouver_QuandoChamarFindAll() {
    LaboratoryController controller = new LaboratoryController(labRepository);
    Laboratory lab = new Laboratory(null, "Agrotis Teste", null);
    
    Mockito.when(labRepository.findAll()).thenReturn(new ArrayList<Laboratory>(List.of(lab)));
    
    ResponseEntity<List<Laboratory>> response = controller.getAll();

    assertEquals(response.getBody().size(), 1);
    assertEquals(response.getBody().getClass(), ArrayList.class);
  }

  @Test
  public void deveRetornarUmConflictQuandoReceberUmLabQueJaEstejaCadastrado_QuandoChamarCreate()
      throws Exception {
    LaboratoryController controller = new LaboratoryController(labRepository);
    Laboratory lab = new Laboratory(null, "Agrotis Teste", null);
    
    Mockito.when(labRepository.findByName("Agrotis Teste"))
      .thenReturn(new ArrayList<Laboratory>(List.of(lab)));
    
    final ResponseEntity<Laboratory> response = controller.create(lab);

    assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
  }

  @Test
  public void deveLancarUmErroAoTentarCadastrarComNomeNull_QuandoChamarCreate() {
    LaboratoryController controller = new LaboratoryController(labRepository);
    Laboratory lab = new Laboratory(null, null, null);

    assertThrows(KeyErrorLaboratory.class, () -> controller.create(lab));
  }

}
