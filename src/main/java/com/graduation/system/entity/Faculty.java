package com.graduation.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "faculties")
@Table(name = "faculties")
public class Faculty extends BaseEntity {
    @NotNull
    private String name;

    @OneToMany(mappedBy = "faculty")
    private Set<User> users;
}
