package com.maglebov.MvcBoot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

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
    @NotEmpty(message = "Поле 'Email' не может быть пустым") // ← исправлено!
    @Email(message = "некорректный email")
    private String email;

    // 🔐 Обязательное поле для Spring Security
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Пароль обязателен")
    private String password;

    // 👥 Связь с ролями — один пользователь может иметь несколько ролей
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {}

    public User(String name, String lastName, String email, String password, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles; // Spring Security использует роли для проверки доступа
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\''+
                ", roles=" + roles +
                '}';
    }
}