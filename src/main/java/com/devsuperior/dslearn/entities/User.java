package com.devsuperior.dslearn.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    //Associação muitos para muitos coloca o set para não haver repetição
    //Um usuario pode ter muitos roles
    // Anotação que mapeia um relacionamento muitos-para-muitos com a entidade Role
    @ManyToMany
    // Anotação que configura a tabela de junção para o relacionamento ManyToMany
    @JoinTable(name = "tb_user_role", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "user_id"), // Coluna na tabela de junção que referencia o ID do usuário
            inverseJoinColumns = @JoinColumn(name = "role_id")) // Coluna na tabela de junção que referencia o ID da role
    private Set<Role> roles = new HashSet<>();

    public User(){

    }

    public User(Long id, String name, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof User user)) return false;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
