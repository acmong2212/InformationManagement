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
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    private IProjectRepository projectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Page<ProjectDTO> searchProject(Search search, Pageable pageable) {
        Page<Project> projectPage = projectRepository.searchProject(search.getCode(), search.getName(), pageable);
        return projectPage.map(ProjectDTO::new);
    }

    @Override
    public ProjectDTO findById(Long id) {
        return projectRepository.findById(id).map(ProjectDTO::new).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<ProjectDTO> search(Search search) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        builder.append("select p ");
        builder.append("from Project p ");
        builder.append("where 1=1 ");
        if (search.getCode() != null) {
            builder.append("and p.code like :code ");
            params.put("code", "%" + search.getCode() + "%");
        }
        if (search.getName() != null) {
            builder.append("and p.name like :name");
            params.put("name", "%" + search.getName() + "%");
        }
        Query query = entityManager.createQuery(builder.toString());
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        List<ProjectDTO> list = query.getResultList();
        return list;
    }
}
