package com.agrotis.agrotis.seeders;

import com.agrotis.agrotis.entities.Propriedade;
import com.agrotis.agrotis.repositories.PropriedadeRepository;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class PropriedadeSeeders implements CommandLineRunner {
  
  PropriedadeRepository propriedadeRepository;

  @Override
  public void run(String... args) throws Exception {
    loadPropriedadeData();
  }

  private void loadPropriedadeData() {
    Propriedade agrotis1 = new Propriedade(null, "agrotis1", "29.541.428/0001-21", null);
    Propriedade agrotis2 = new Propriedade(null, "agrotis2", "29.541.428/0001-22", null);
    Propriedade agrotis3 = new Propriedade(null, "agrotis3", "29.541.428/0001-23", null);

    List<Propriedade> propriedades = new ArrayList<Propriedade>(List.of(
        agrotis1, agrotis2, agrotis3
    ));
    if (propriedadeRepository.count() == 0) {
      for (Propriedade propriedade : propriedades) {
        propriedadeRepository.save(propriedade);
      }
    }
  }
}