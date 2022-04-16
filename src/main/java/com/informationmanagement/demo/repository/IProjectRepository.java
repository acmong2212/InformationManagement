package com.informationmanagement.demo.repository;

import com.informationmanagement.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p from Project p where p.code like %:code% and p.name like %:name%")
    List<Project> searchProject(String code, String name);
}
