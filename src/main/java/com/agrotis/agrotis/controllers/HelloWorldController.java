package com.agrotis.agrotis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "Bem vindo à API do desafio tecnico da agrotis, "
      + "os endpoints acessiveis são: /ownership, /users e /laboratories e suas variações {name}"
      + " ou {id}";
  }
}