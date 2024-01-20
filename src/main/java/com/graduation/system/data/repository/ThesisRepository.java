package com.graduation.system.data.repository;

import com.graduation.system.data.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {
}
