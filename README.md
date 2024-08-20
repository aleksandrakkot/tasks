# Aplikacja do Zarządzania Zadaniami

Jest to prosta aplikacja CRUD (Create, Read, Update, Delete) do zarządzania zadaniami. Aplikacja została zbudowana przy użyciu Spring Boot, z bazą danych MySQL do trwałego przechowywania danych.

## Funkcjonalności

- **Dodawanie zadań**: Możliwość tworzenia nowych zadań.  
  **Metoda HTTP**: `POST`  
  **Endpoint**: `/v1/tasks`

- **Przeglądanie zadań**: Wyświetlanie listy wszystkich zadań.  
  **Metoda HTTP**: `GET`  
  **Endpoint**: `/v1/tasks`

- **Pobieranie konkretnego zadania**: Możliwość pobrania szczegółów konkretnego zadania na podstawie jego identyfikatora.  
  **Metoda HTTP**: `GET`  
  **Endpoint**: `/v1/tasks/{taskId}`

- **Aktualizacja zadań**: Edytowanie istniejących zadań.  
  **Metoda HTTP**: `PUT`  
  **Endpoint**: `/v1/tasks`

- **Usuwanie zadań**: Usuwanie zadań z bazy danych.  
  **Metoda HTTP**: `DELETE`  
  **Endpoint**: `/v1/tasks/{id}`


## Ekran początkowy
![image](https://github.com/user-attachments/assets/6ae0e198-69d1-48d2-b934-10c408a096c0)

## Technologia

- **Język programowania**: Java 17
- **Framework**: Spring Boot 3.0
- **Baza danych**: MySQL
- **Biblioteki**: Lombok, Spring Data JPA, Hibernate
