package com.agrotis.agrotis.seeders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.agrotis.agrotis.Entities.Laboratorio;
import com.agrotis.agrotis.Entities.Propriedade;
import com.agrotis.agrotis.Entities.User;
import com.agrotis.agrotis.repositories.LaboratorioRepository;
import com.agrotis.agrotis.repositories.PropriedadeRepository;
import com.agrotis.agrotis.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserSeeders implements CommandLineRunner {
  
  UserRepository userRepository;
  LaboratorioRepository laboratorioRepository;
  PropriedadeRepository propriedadeRepository;

  @Override
  public void run(String... args) throws Exception {
    LoadUserData();
  }

  private void LoadUserData() {
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
