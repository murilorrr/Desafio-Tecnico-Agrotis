package com.agrotis.agrotis.seeders;

import com.agrotis.agrotis.entities.Laboratorio;
import com.agrotis.agrotis.repositories.LaboratorioRepository;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class LaboratorioSeeders implements CommandLineRunner {

  LaboratorioRepository laboratorioRepository;

  @Override
  public void run(String... args) throws Exception {
    loadLaboratorioData();
  }

  private void loadLaboratorioData() {
    Laboratorio lab1 = new Laboratorio(null, "Agro Skynet", null);
    Laboratorio lab2 = new Laboratorio(null, "Umbrella Agro", null);
    Laboratorio lab3 = new Laboratorio(null, "Osborn Agro", null);
  
    List<Laboratorio> laboratorios = new ArrayList<Laboratorio>(List.of(lab1, lab2, lab3));
    
    if (laboratorioRepository.count() == 0) {
      for (Laboratorio laboratorio : laboratorios) {
        laboratorioRepository.save(laboratorio);
      }
    }
  }

}
