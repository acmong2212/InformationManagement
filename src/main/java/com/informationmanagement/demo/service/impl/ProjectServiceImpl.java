package com.informationmanagement.demo.service.impl;

import com.informationmanagement.demo.dto.response.ProjectDTO;
import com.informationmanagement.demo.dto.request.Search;
import com.informationmanagement.demo.model.Project;
import com.informationmanagement.demo.model.Project_;
import com.informationmanagement.demo.repository.ProjectRepository;
import com.informationmanagement.demo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //@todo validate dữ liệu đầu vào bên DTO
    @Override
    public ProjectDTO save(ProjectDTO project) {
        Project p = new Project(project);
        Project projectE = projectRepository.save(p);
        return new ProjectDTO(projectE);
    }

    //@todo validate dữ liệu đầu vào bên DTO
    @Override
    public ProjectDTO update(Long id, ProjectDTO project) {
        Project p =  projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "Project does not exist"));
        project.setId(id);
        p.update(project);
        projectRepository.save(p);
        return new ProjectDTO(p);
    }

    @Override
    public void deleteById(Long id) {
        Project project = projectRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("Project", "Project does not exist"));
        project.setDeleted(true);
        projectRepository.save(project);
    }

    @Override
    public ProjectDTO findById(Long id) {
        return projectRepository.findByIdAndDeletedIsFalse(id).map(ProjectDTO::new).orElseThrow(() -> new ResourceNotFoundException("Project", "Project does not exist"));
}

    // Query động dùng string builder
    @Override
    public Page<ProjectDTO> search(Search search, int page1, int size) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        Pageable pageable = PageRequest.of(page1 - 1, size);

        hql.append("select p ");
        hql.append("from Project p ");
        hql.append("where 1=1 ");
        hql.append("and deleted = 0 ");
        if (search.getCode() != null) {
            hql.append("and p.code like :code ");
            params.put("code", "%" + search.getCode() + "%");
        }
        if (search.getName() != null) {
            hql.append("and p.name like :name");
            params.put("name", "%" + search.getName() + "%");
        }
        Query query = entityManager.createQuery(hql.toString()).setMaxResults(size).setFirstResult((int)pageable.getOffset());
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }

        List<ProjectDTO> list = query.getResultList();
        Page<ProjectDTO> page = new PageImpl<>(list, pageable, list.size());
        return page;
    }

    @Override
    public int count(Search search) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("select count(*) ");
        hql.append("from Project p ");
        hql.append("where 1=1 ");
        hql.append("and deleted = 0 ");
        if (search.getCode() != null) {
            hql.append("and p.code like :code ");
            params.put("code", "%" + search.getCode() + "%");
        }
        if (search.getName() != null) {
            hql.append("and p.name like :name");
            params.put("name", "%" + search.getName() + "%");
        }
        Query query = entityManager.createQuery(hql.toString());
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        return (query.getSingleResult().hashCode());
    }

    // Query động dùng Criteria
    @Override
    public List<ProjectDTO> findByCodeAndNameAndTeamSizeCriteria(String code, String name, Long teamSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> query = builder.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);

        Predicate hasCode = builder.like(root.get(Project_.CODE), code);
        Predicate hasName = builder.like(root.get(Project_.NAME), name);
        Predicate hasTeamSize = builder.equal(root.get(Project_.TEAM_SIZE), teamSize);

        Predicate condition = builder.or(hasCode, hasName, hasTeamSize);
        query.select(root).where(condition);
        List<Project> projectList = entityManager.createQuery(query).getResultList();
        return projectList.stream().map(ProjectDTO::new).collect(Collectors.toList());
    }
}
