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
public class UserSeeders implements CommandLineRunner {
  
  UserRepository userRepository;
  LaboratorioRepository laboratorioRepository;
  PropriedadeRepository propriedadeRepository;

  @Override
  public void run(String... args) throws Exception {
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
}
