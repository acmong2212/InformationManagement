package com.informationmanagement.demo.repository;

import com.informationmanagement.demo.dto.Search;
import com.informationmanagement.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    @Query(nativeQuery = true, value = "select * from `project-information`.project as p " +
            "where 1 " +
            "and p.code_project like %E% " +
            "and p.name_project like %t% " +
            "and p.time_start = '2018-12-31'")
    List<Project> searchProject(Search search);


}
