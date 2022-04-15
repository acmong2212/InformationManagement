package com.informationmanagement.demo.controller;

import com.informationmanagement.demo.dto.Search;
import com.informationmanagement.demo.model.Project;
import com.informationmanagement.demo.service.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/project")
public class ProjectController {

    private IProjectService projectService;

    @GetMapping("/list")
    public ResponseEntity<?> findAllProject() {
        return new ResponseEntity<>(projectService.findAllProject(), HttpStatus.OK);
    }

    @PostMapping("/createProject")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        projectService.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateProject/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
        project.setId(id);
        projectService.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody Search search) {
        projectService.searchProject(search);
        return new ResponseEntity<>(projectService.findAllProject(), HttpStatus.OK);
    }

    
}
