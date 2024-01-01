package com.graduation.system.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DefenseId implements Serializable {
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "tag_id")
    private Long tagId;
}
