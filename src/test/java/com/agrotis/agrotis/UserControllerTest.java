package com.agrotis.agrotis;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.agrotis.agrotis.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserRepository mockUserRepo;

  @Test
  void deve_RetornarListaVazia_Quando_NaoHouverUsers() throws Exception {
    mockMvc.perform(get("/users"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk())
      .andExpect(content().json("{users: []}"));
  }

  @Test
  void deve_ser_possivel_adicionar_usuarios() throws Exception {

    // InputStream getLocalJsonFile = new FileInputStream(
      // "src/test/java/com/agrotis/agrotis/user.json");
    // HashMap<String,String> jsonMap = new ObjectMapper().
    // readValue(getLocalJsonFile, HashMap.class);

    // mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
    // .content(jsonMap.toString()))
    // .andExpect(status().isCreated());
  }

}
