package com.informationmanagement.demo.controller;

import com.informationmanagement.demo.dto.ProjectDTO;
import com.informationmanagement.demo.dto.Search;
import com.informationmanagement.demo.model.Project;
import com.informationmanagement.demo.service.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/projects")
public class ProjectController {

    private IProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        projectService.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
        project.setId(id);
        projectService.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> search(@RequestParam(required = false) String code,
                                    @RequestParam(required = false) String name) {
        Search s = new Search();
        s.setCode(code);
        s.setName(name);
        return new ResponseEntity<>(projectService.searchProject(s), HttpStatus.OK);
    }

    @GetMapping("/s")
    public ResponseEntity<?> search(@RequestParam(required = false) String code,
                                    @RequestParam(required = false) String name,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Search s = new Search();
        s.setCode(code);
        s.setName(name);
        return new ResponseEntity<>(projectService.searchProject(s, pageable), HttpStatus.OK);
    }
}
