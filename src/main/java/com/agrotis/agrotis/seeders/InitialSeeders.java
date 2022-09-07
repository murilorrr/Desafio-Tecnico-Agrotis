package com.agrotis.agrotis.seeders;

import com.agrotis.agrotis.entities.Laboratory;
import com.agrotis.agrotis.entities.Property;
import com.agrotis.agrotis.entities.User;
import com.agrotis.agrotis.repositories.LaboratoryRepository;
import com.agrotis.agrotis.repositories.PropertyRepository;
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
  LaboratoryRepository laboratoryRepository;
  PropertyRepository propertyRepository;

  @Override
  public void run(String... args) {
    loadPropertyData();
    loadLaboratoryData();
    loadUserData();
  }

  private void loadUserData() {
    LocalDate date = LocalDate.now();

    List<Laboratory> labs = laboratoryRepository.findAll();
    List<Property> props = propertyRepository.findAll();

    User maria = new User(null, "Maria", date, date, "comments1", labs.get(0), props.get(2));
    User jorge = new User(null, "Jorge", date, date, "comments2", labs.get(1), props.get(1));
    User joao = new User(null, "Joao", date, date, "comments3", labs.get(2), props.get(0));
    List<User> users = new ArrayList<>(List.of(maria, joao, jorge));
    if (userRepository.count() == 0) {
      userRepository.saveAll(users);
    }
  }
  
  private void loadLaboratoryData() {
    Laboratory lab1 = new Laboratory(null, "Agro Skynet", null);
    Laboratory lab2 = new Laboratory(null, "Umbrella Agro", null);
    Laboratory lab3 = new Laboratory(null, "Osborn Agro", null);
  
    List<Laboratory> laboratories = new ArrayList<>(List.of(lab1, lab2, lab3));
    
    if (laboratoryRepository.count() == 0) {
      laboratoryRepository.saveAll(laboratories);
    }
  }

  private void loadPropertyData() {
    Property agrotis1 = new Property(null, "agrotis1", "29.541.428/0001-21", null);
    Property agrotis2 = new Property(null, "agrotis2", "29.541.428/0001-22", null);
    Property agrotis3 = new Property(null, "agrotis3", "29.541.428/0001-23", null);

    List<Property> properties = new ArrayList<>(List.of(
        agrotis1, agrotis2, agrotis3
    ));
    if (propertyRepository.count() == 0) {
      propertyRepository.saveAll(properties);
    }
  }
}
