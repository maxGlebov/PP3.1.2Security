package com.maglebov.MvcBoot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Поле 'Имя' не может быть пустым")
    @Size(min = 2, max = 20, message = "Поле 'Имя' должно быть от 2 до 20 символов")
    private String name;

    @Column(name = "lastName")
    @NotEmpty(message = "Поле 'Фамилия' не может быть пустым")
    @Size(min = 2, max = 20, message = "Поле 'Фамилия' должно быть от 2 до 20 символов")
    private String lastName;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "Поле 'Имя' не может быть пустым")
    @Email(message = "некорректный email")
    private String email;

    public User() {
    }

    public User(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}