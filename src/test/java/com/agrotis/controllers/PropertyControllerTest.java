package com.agrotis.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.agrotis.agrotis.controllers.PropertyController;
import com.agrotis.agrotis.entities.Property;
import com.agrotis.agrotis.exceptions.KeyErrorProperty;
import com.agrotis.agrotis.exceptions.ErrorPropertyNotFound;
import com.agrotis.agrotis.repositories.PropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
@DisplayName("Teste de unidade para o controller de propriedades")
public class PropertyControllerTest {

  @MockBean
  private PropertyController controller;

  @MockBean
  private PropertyRepository propertyRepository;
  
  @Test
  public void deveConterUmaListaDePropriedades_QuandoChamarFindAll() {
    PropertyController controller = new PropertyController(propertyRepository);
    
    Mockito.when(propertyRepository.findAll()).thenReturn(new ArrayList<>());

    ResponseEntity<List<Property>> response = controller.getAll();
    
    assertEquals(Objects.requireNonNull(response.getBody()).size(), 0);
    assertEquals(response.getBody().getClass(), ArrayList.class);
  }

  @Test
  public void deveConterUmaListaDePropriedadesComPeloMenosUmQuandoHouver_QuandoChamarFindAll() {
    PropertyController controller = new PropertyController(propertyRepository);
    Property prop = new Property(null, "Prop Teste", "CNPJ test", null);
    
    Mockito.when(propertyRepository.findAll())
      .thenReturn(new ArrayList<>(List.of(prop)));
    
    ResponseEntity<List<Property>> response = controller.getAll();

    assertEquals(Objects.requireNonNull(response.getBody()).size(), 1);
    assertEquals(response.getBody().getClass(), ArrayList.class);
  }

  @Test
  public void deveRetornarUmConflictQuandoReceberUmLabQueJaEstejaCadastrado_QuandoChamarCreate()
      throws Exception {
    PropertyController controller = new PropertyController(propertyRepository);
    Property prop = new Property(null, "Agrotis Teste", "CNPJ teste", null);
    
    Mockito.when(propertyRepository.findByName("Agrotis Teste"))
      .thenReturn(new ArrayList<>(List.of(prop)));
    
    final ResponseEntity<Property> response = controller.create(prop);

    assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
  }

  @Test
  public void deveLancarUmErroAoTentarCadastrarComNomeNull_QuandoChamarCreate() {
    PropertyController controller = new PropertyController(propertyRepository);
    Property lab = new Property(null, null,"CNPJ teste", null);

    assertThrows(KeyErrorProperty.class, () -> controller.create(lab));
  }

  @Test
  public void deveLancarUmErroAoTentarCadastrarComCnpjNull_QuandoChamarCreate() {
    PropertyController controller = new PropertyController(propertyRepository);
    Property lab = new Property(null, "Agrotis teste", null, null);

    assertThrows(KeyErrorProperty.class, () -> controller.create(lab));
  }

  @Test
  public void deveRetornarErroAoProcurarUmaPropriedadeQueNaoExiste_QuandoChamarFindOneByName() {
    PropertyController controller = new PropertyController(propertyRepository);

    Mockito.when(propertyRepository.findOneByName("")).thenReturn(Optional.empty());

    assertThrows(ErrorPropertyNotFound.class, () -> controller.getOneByName(""));
  }
}
