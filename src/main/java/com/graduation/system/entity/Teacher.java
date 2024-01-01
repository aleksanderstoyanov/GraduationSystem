package com.graduation.system.entity;

import com.graduation.system.entity.enums.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@Table
@Entity
@AllArgsConstructor
public class Teacher extends BaseEntity {

    @NotNull(message = TeacherMessage.PositionNotNull)
    @Column(name = "position")
    private Position Position;

    @NotNull(message = CommonMessage.NameNotNull)
    @Column(name = "name")
    @Size(min = 4, max = 15, message = CommonMessage.NameLength)
    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Application> diplomaApplications;

    @OneToMany(
            mappedBy = "teacher",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Defense> defenses;
}
