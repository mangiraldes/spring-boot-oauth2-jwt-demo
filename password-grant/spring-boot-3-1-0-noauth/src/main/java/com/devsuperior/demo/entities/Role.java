package com.devsuperior.demo.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_role")
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;


    public Role(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(authority, role.authority);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(authority);
        return result;
    }
}





