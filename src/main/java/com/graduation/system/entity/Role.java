package com.graduation.system.entity;

import com.graduation.system.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {
    @NotNull
    @Column(name = "role")
    private UserRole role = UserRole.NONE;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
