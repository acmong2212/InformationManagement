package com.informationmanagement.demo.service;

import com.informationmanagement.demo.dto.response.ProjectDTO;
import com.informationmanagement.demo.dto.request.Search;
import com.informationmanagement.demo.model.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    ProjectDTO save(ProjectDTO project);
    ProjectDTO update(Long id, ProjectDTO project);
    void deleteById(Long id);
    ProjectDTO findById(Long id);
    Page<ProjectDTO> search(Search search, int page, int size);
    int count(Search search);

    List<ProjectDTO> findByCodeAndNameAndTeamSizeCriteria(String code, String name, Long teamSize);
}
