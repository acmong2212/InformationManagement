package com.informationmanagement.demo.service.impl;

import com.informationmanagement.demo.dto.Search;
import com.informationmanagement.demo.model.Project;
import com.informationmanagement.demo.repository.IProjectRepository;
import com.informationmanagement.demo.service.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }
}
