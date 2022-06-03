# Agrotis API

<p align="center">
  <a href="https://codeclimate.com/github/murilorsv14/Desafio-Tecnico-Agrotis/maintainability"><img src="https://api.codeclimate.com/v1/badges/d1cb27ba7514651179e9/maintainability" /></a>
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/murilorsv14/Desafio-Tecnico-Agrotis?color=6E40C9">
  <img alt="GitHub Top Language" src="https://img.shields.io/github/languages/top/murilorsv14/Desafio-Tecnico-Agrotis?color=6E40C9">
  <a href="https://github.com/murilorsv14/Delivery-App/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/murilorsv14/Desafio-Tecnico-Agrotis?color=6E40C9">
  </a>
  <img alt="GitHub issues" src="https://img.shields.io/github/issues/murilorsv14/Desafio-Tecnico-Agrotis">
</p>

#### Preview Sketch
<img src="https://github.com/murilorsv14/Desafio-Tecnico-Agrotis/blob/main/print.png?raw=true" width="auto" title="Image sketch">

<p align="center">A modern resource API</p>

<p align="center"><i> "How to save a agro economy?" - Thomas Malthus </i> </p>

<p align="center">
  <a href="#-why">Why</a> •
  <a href="#-tech-stack">Tech Stack</a> •
  <a href="#-contributing">Contributing</a> •
  <a href="https://github.com/murilorsv14">About Me</a> •
  <a href="https://agrotis-api-2765.herokuapp.com/">Deployed in heroku</a>
</p>

## 😊 **Why?**

<h4>Description:</h4>
<p>
The test consists of building an API to be used in the construction of a screen that is on the <a href="#-Preview Sketch">Preview</a>.

The API must perform the necessary validations to ensure data integrity and
expose the services needed for listing, searching, inserting, updating and deleting
data (CRUD).

Check the need for additional services to populate ListViews (Property and Laboratory)
</p>
<h4>Requirements:</h4>
<p>
Develop with Java, Spring Boot.
Use some form of data persistence.
</p>

<h4>Optionals:</h4>
<p>
Send the postman collection with the tests performed.
Unit tests.
</p>

Now it's hands to work! Let's start?

## ⚡ **Tech Stack**

<h1 align="center">
  <img src="https://miro.medium.com/max/1185/1*gbOHRlqC7Nha-omsHMIBRw.png" alt="Stack" height="300" width="1000">
  <br>
</h1>

- [Spring](https://spring.io/)
- [Docker](https://www.docker.com/)
- [MySQL](https://www.mysql.com/)
- [CheckStyle](https://checkstyle.sourceforge.io/)

and much more...

## API

| Method | Route | Description | Type of Param | Params |
|-------| ------ | ---- | ------ | ---- | 
|GET| /users       |  Retorn all of users in database    |  |  |
|GET| /users/:id       |  Retorn a user in database with id  |   Param String   |  id  |
|POST| /users       |   create new user   |   Body    |   name, initialDate, endDate, ?comments, laboratorio, propriedade   |
|DELETE| /users/:id       |   delete user   |   Param String   |   id   |
|PUT| /users/:id       |   delete user   |   Param String, Body   |   id, name, initialDate, endDate, ?comments, laboratorio, propriedade   |
|GET| /laboratories       |  Retorn all of laboratorios in database    |  |  |
|GET| /laboratories/:name       |  Retorn a laboratio in database with name    |   Param String   |  name  |
|POST| /laboratories       |   create new laboratorio   |   Body    |  name   |
|GET| /ownership       |  Retorn all of propriedades in database    |  |  |
|GET| /ownership/:name       |  Retorn a propriedade in database with name    |   Param String   |  name  |
|POST| /ownership       |   create new propriedade   |   Body    |  name, cnpj   |

Exempo de requisição POST para Propriedade: 
```
  {
    "name": "Matrix Agro",
    "cnpj": "29.541.428/0001-29"
  }
```

Exempo de requisição POST para Laboratorio: 
```
  {
    "name": "agrotis122"
  }
```

Exempo de requisição POST para Usuários:
```
  {
    "name": "jorge",
    "initialDate": "2017-01-12",
    "endDate": "2017-01-14",
    "comments": "",
    "laboratorio": "Agro Skynet",
    "propriedade": "agrotis1"
  }
```

## 🏃 Getting started

### Pre-requisites

- _Java:_ `openjdk version "11.0.14.1"` or higher.
- _Apache Maven:_ `3.6.3` or higher.
- _Git:_ `2.25.1` or higher.
- _Docker(Optional):_ `20.10.14` or higher.

Clone the project from Github :

```sh
$ git clone git@github.com:murilorsv14/Desafio-Tecnico-Agrotis.git
$ cd Desafio-Tecnico-Agrotis
```

### 🐳 Docker Method

If you have docker installed, you can run with:

```sh 
$ docker-compose up (not funcional yet)
$ mvn spring-boot:run
```

or

```sh 
$ docker build -t mysql-database ./db
$ docker run -p 3306:3306 mysql-database
$ docker build -t app-agrotis .
$ docker run -p 8080:8080 app-agrotis
```

Pre-requisites
If everything is ok, you will be able to open at:

```bash
http://localhost:8080/users
```

### Default method

#### You Need MySQL running with agrotis database Install backend folder dependencies and start Spring server:

```sh
$ cd Desafio-Tecnico-Agrotis
$ mvn install
$ mvn spring-boot:run
```

If everything is ok, you will be able to open at:

```bash
http://localhost:8080/users
```

## 👣 Next steps

- [X] Deploy on heroku
- [X] API documentation
- [ ] Improve security with Spring Security and JWT
- [ ] Improve JSON validation with Spring Validation
- [ ] Test for at least 90% of the application

## 🤝 **Contributing**

This project is for study purposes too, so send me an email telling me what you are doing and why you are doing it, teach me what you know

All kinds of contributions are very welcome and appreciated!

- ⭐️ Star the project
- 🐛 Find and report issuesapp-agrotis
- 📥 Submit PRs to help solve issues or add features
- ✋ Influence the future of Desafio-Tecnico-Agrotis with feature requests

<!-- To learn how to contribute, read the [Contributing Guide](/CONTRIBUTING.md) before making the pull request. -->

<!-- To learn a little more about the implemented architecture, read the [Architecture Design](/ARCHITECTURE.md). -->
