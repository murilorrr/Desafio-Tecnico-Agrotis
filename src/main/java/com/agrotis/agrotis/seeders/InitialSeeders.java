package com.agrotis.agrotis.seeders;

import com.agrotis.agrotis.entities.Laboratorio;
import com.agrotis.agrotis.entities.Propriedade;
import com.agrotis.agrotis.entities.User;
import com.agrotis.agrotis.repositories.LaboratorioRepository;
import com.agrotis.agrotis.repositories.PropriedadeRepository;
import com.agrotis.agrotis.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitialSeeders implements CommandLineRunner {
  
  UserRepository userRepository;
  LaboratorioRepository laboratorioRepository;
  PropriedadeRepository propriedadeRepository;

  @Override
  public void run(String... args) throws Exception {
    loadPropriedadeData();
    loadLaboratorioData();
    loadUserData();
  }

  private void loadUserData() {
    LocalDate date = LocalDate.now();

    List<Laboratorio> labs = laboratorioRepository.findAll();
    List<Propriedade> props = propriedadeRepository.findAll();

    User maria = new User(null, "Maria", date, date, "comments1", labs.get(0), props.get(2));
    User jorge = new User(null, "Jorge", date, date, "comments2", labs.get(1), props.get(1));
    User joao = new User(null, "Joao", date, date, "comments3", labs.get(2), props.get(0));
    List<User> users = new ArrayList<User>(List.of(maria, joao, jorge));
    if (userRepository.count() == 0) {
      for (User user : users) {
        userRepository.save(user);
      }
    }
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
