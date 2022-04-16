package com.informationmanagement.demo.controller;

import com.informationmanagement.demo.dto.Search;
import com.informationmanagement.demo.model.Project;
import com.informationmanagement.demo.service.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findById(@PathVariable Long id) {
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

    @GetMapping("/s")
    public ResponseEntity<?> search1(@RequestParam(required = false) String code, String name) {
        Search s = new Search();
        s.setCode(code);
        s.setName(name);
        return new ResponseEntity<>(projectService.searchProject(s), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> search(@RequestBody Search search) {
        return new ResponseEntity<>(projectService.searchProject(search), HttpStatus.OK);
    }


}
