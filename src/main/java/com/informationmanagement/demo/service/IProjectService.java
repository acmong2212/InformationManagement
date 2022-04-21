package com.informationmanagement.demo.service;

import com.informationmanagement.demo.dto.response.ProjectDTO;
import com.informationmanagement.demo.dto.request.Search;
import com.informationmanagement.demo.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProjectService {
    Project save(Project project);
    void deleteById(Long id);
    ProjectDTO findById(Long id);
    Boolean existsById(Long id);
    Page<ProjectDTO> search(Search search, Pageable pageable);
}
