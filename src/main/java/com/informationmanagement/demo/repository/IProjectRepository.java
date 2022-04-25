package com.informationmanagement.demo.repository;

import com.informationmanagement.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByIdAndDeletedIsFalse(Long id);
}
