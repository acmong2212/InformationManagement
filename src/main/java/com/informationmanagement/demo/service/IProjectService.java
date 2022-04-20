package com.informationmanagement.demo.service;

import com.informationmanagement.demo.dto.ProjectDTO;
import com.informationmanagement.demo.dto.Search;
import com.informationmanagement.demo.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProjectService {
    Project save(Project project);
    void deleteById(Long id);
    Page<ProjectDTO> searchProject(Search search, Pageable pageable);
    ProjectDTO findById(Long id);
    List<ProjectDTO> search(Search search);
}
