# 🎬 Cine API - Backend
Este repositorio contiene el backend de una API REST desarrollada en Java 17 con Spring Boot, conectada a una base de datos MySQL. El proyecto permite gestionar cines y películas, contemplando una relación muchos-a-muchos entre ambas entidades mediante una tabla intermedia.

## 🧱 Tecnologías utilizadas
- Java 17

- Spring Boot

- Spring Data JPA

- MySQL

- Maven

## 📚 Entidades del sistema
### 🏢 Cine
Representa un cine físico.

Atributos:

- id: identificador único

- nombre: nombre del cine

- direccion: dirección física

- ciudad: ciudad donde se encuentra el cine

### 🎬 Película
Representa una película proyectada en uno o más cines.

- Atributos:

- id: identificador único

- titulo: título de la película

- genero: género cinematográfico

- duracionMin: duración en minutos

### 🔗 PeliculaCine
Entidad intermedia que representa qué película se proyecta en qué cine.

Atributos:

- id: identificador único (o clave compuesta)

- peliculaId: referencia a la película

- cineId: referencia al cine