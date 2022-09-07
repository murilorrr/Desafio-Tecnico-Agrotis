package com.agrotis.agrotis.services;

import com.agrotis.agrotis.entities.Laboratory;
import com.agrotis.agrotis.entities.Property;
import com.agrotis.agrotis.entities.User;
import com.agrotis.agrotis.entities.UserRequest;
import com.agrotis.agrotis.exceptions.KeyErrorDate;
import com.agrotis.agrotis.exceptions.KeyErrorName;
import com.agrotis.agrotis.exceptions.KeyError;
import com.agrotis.agrotis.exceptions.ErrorUserNotFound;
import com.agrotis.agrotis.repositories.LaboratoryRepository;
import com.agrotis.agrotis.repositories.PropertyRepository;
import com.agrotis.agrotis.repositories.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserServiceInterface {

  private final UserRepository userRepository;
  private final LaboratoryRepository laboratoryRepository;
  private final PropertyRepository propertyRepository;

  @Autowired
  public UserService(UserRepository userRepository, LaboratoryRepository laboratoryRepository, PropertyRepository propertyRepository) {
    this.userRepository = userRepository;
    this.laboratoryRepository = laboratoryRepository;
    this.propertyRepository = propertyRepository;
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
  
  @Override
  public User findById(Long id) throws ErrorUserNotFound {
    return userRepository.findById(id)
      .orElseThrow(ErrorUserNotFound::new);
  }

  @Override
  public User create(UserRequest userRequest) throws KeyError {

    Laboratory lab = null;
    Property prop = null;

    if (userRequest.getName() == null) {
      throw new KeyErrorName();
    }

    if ((userRequest.getInitialDate() == null) || (userRequest.getEndDate() == null)) {
      throw new KeyErrorDate();
    }

    if (laboratoryRepository.findOneByName(userRequest.getLaboratory()).isPresent())
      lab = laboratoryRepository.findOneByName(userRequest.getLaboratory()).get();


    if (propertyRepository.findOneByName(userRequest.getProperty()).isPresent())
      prop = propertyRepository.findOneByName(userRequest.getProperty()).get();

    
    User user = new User();
    user.setName(userRequest.getName());
    user.setInitialDate(userRequest.getInitialDate());
    user.setEndDate(userRequest.getEndDate());
    user.setLaboratory(lab);
    user.setProperty(prop);
    user.setComments(userRequest.getComments());

    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) throws ErrorUserNotFound {
    try {
      userRepository.deleteById(id);
    } catch (IllegalArgumentException e) {
      throw new ErrorUserNotFound();
    }
  }

  @Override
  public User update(UserRequest userRequest, Long id) throws ErrorUserNotFound {
    User user = findById(id);

    Laboratory lab = null;
    Property prop = null;

    if (laboratoryRepository.findOneByName(userRequest.getLaboratory()).isPresent())
      lab = laboratoryRepository.findOneByName(userRequest.getLaboratory()).get();

    if (propertyRepository.findOneByName(userRequest.getProperty()).isPresent())
      prop = propertyRepository.findOneByName(userRequest.getProperty()).get();

    user.setLaboratory(lab);
    user.setProperty(prop);

    user.setName(userRequest.getName());
    user.setInitialDate(userRequest.getInitialDate());
    user.setEndDate(userRequest.getEndDate());
    user.setComments(userRequest.getComments());
    return userRepository.save(user);
  }
}
