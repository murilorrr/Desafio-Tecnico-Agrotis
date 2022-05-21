// package com.agrotis.agrotis;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.time.LocalDate;

// import com.agrotis.agrotis.Entities.User;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;

// public class UserTest {

//   @Test
//   @DisplayName("Teste dos atributos de User")
//   public void testAtributosUser() {
//     LocalDate data = LocalDate.now();
//     Long id = (long) 1;
//     User user = new User(id, "jorge", data, data, "obs: 'registro criado'");
  
//     System.out.println(user.getComments());
//     assertTrue(user.getComments().equals("obs: 'registro criado'"));
//     assertTrue(user.getId().equals(id));
//     assertTrue(user.getEndDate().equals(data));
//     assertTrue(user.getInitialDate().equals(data));
//     assertTrue(user.getName().equals("jorge"));
//   }
// }
