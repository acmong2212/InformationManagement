package com.informationmanagement.demo.service.impl;

import com.informationmanagement.demo.dto.ProjectDTO;
import com.informationmanagement.demo.dto.Search;
import com.informationmanagement.demo.model.Project;
import com.informationmanagement.demo.repository.IProjectRepository;
import com.informationmanagement.demo.service.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    private IProjectRepository projectRepository;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> searchProject(Search search) {
        return projectRepository.searchProject(search.getCode(), search.getName());
    }

    @Override
    public Page<Project> searchProject(Search search, Pageable pageable) {
        return projectRepository.searchProject(search.getCode(), search.getName(), pageable);
    }

    @Override
    public ProjectDTO findById(Long id) {
        return projectRepository.findById(id).map(ProjectDTO::new).orElseThrow(() -> new RuntimeException());
    }
}
