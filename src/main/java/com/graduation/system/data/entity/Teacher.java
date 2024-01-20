package com.graduation.system.data.entity;

import com.graduation.system.data.enums.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import static com.graduation.system.messages.EntityMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "teachers")
@Table(name = "teachers")
public class Teacher extends BaseEntity {

    @NotNull(message = TeacherMessage.PositionNotNull)
    @Column(name = "position")
    private Position Position;

    @Column(name = "egn")
    @Size(min = 10, max = 10, message = "EGN should be exactly 10 symbols")
    private String egn;

    @OneToMany(mappedBy = "teacher")
    private List<Application> applications;

    @OneToMany(
            mappedBy = "teacher",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Defense> defenses;

    @OneToOne
    @JoinColumn(name = "user_egn")
    private User user;
}
