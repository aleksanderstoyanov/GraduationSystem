package com.graduation.system.entity;

import com.graduation.system.messages.EntityMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class User extends BaseEntity  {
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;

    @NotNull(message = EntityMessages.CommonMessage.NameNotNull)
    @Column(name = "firstName")
    @Size(min = 4, max = 15, message = EntityMessages.CommonMessage.NameLength)
    private String firstName;

    @NotNull(message = EntityMessages.CommonMessage.NameNotNull)
    @Column(name = "lastName")
    @Size(min = 4, max = 15, message = EntityMessages.CommonMessage.NameLength)
    private String lastName;

    @Column(name="egn")
    @Size(min = 10, max = 10, message = "EGN should be exactly 10 symbols")
    private String egn;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();
}
