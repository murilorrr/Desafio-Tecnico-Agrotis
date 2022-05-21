package com.agrotis.agrotis.seeders;

import java.util.ArrayList;
import java.util.List;

import com.agrotis.agrotis.Entities.Laboratorio;
import com.agrotis.agrotis.repositories.LaboratorioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LaboratorioSeeders implements CommandLineRunner {

  LaboratorioRepository laboratorioRepository;

  @Override
  public void run(String... args) throws Exception {
    LoadLaboratorioData();
  }

  private void LoadLaboratorioData() {
    Laboratorio lab1 = new Laboratorio(null, "Agro Skynet" , null);
    Laboratorio lab2 = new Laboratorio(null, "Umbrella Agro" , null);
    Laboratorio lab3 = new Laboratorio(null, "Osborn Agro" , null);
  
    List<Laboratorio> laboratorios = new ArrayList<Laboratorio>(List.of(lab1, lab2, lab3));
    
    if (laboratorioRepository.count() == 0) {
      for (Laboratorio laboratorio : laboratorios) {
        laboratorioRepository.save(laboratorio);
      }
    }
  }

}
