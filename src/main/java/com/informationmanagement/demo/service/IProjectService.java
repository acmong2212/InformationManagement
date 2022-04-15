package com.informationmanagement.demo.service;

import com.informationmanagement.demo.dto.Search;
import com.informationmanagement.demo.model.Project;

import java.time.LocalDate;
import java.util.List;

public interface IProjectService {
    List<Project> findAllProject();
    Project save(Project project);
    void deleteById(Long id);
    List<Project> searchProject(Search search);
}
