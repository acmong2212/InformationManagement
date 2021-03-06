package com.informationmanagement.demo.controller;

import com.informationmanagement.demo.dto.response.Message;
import com.informationmanagement.demo.dto.response.ProjectDTO;
import com.informationmanagement.demo.dto.request.Search;
import com.informationmanagement.demo.dto.response.SuccessResponse;
import com.informationmanagement.demo.dto.response.SuccessResponsePage;
import com.informationmanagement.demo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectDTO projectRequest) {
        return new ResponseEntity<>(new SuccessResponse(1, projectService.save(projectRequest)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new SuccessResponse(1, projectService.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Long id, @RequestBody ProjectDTO projectRequest) {
        return new ResponseEntity<>(new SuccessResponse(1, projectService.update(id, projectRequest)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        projectService.deleteById(id);
        return new ResponseEntity<>(new Message(1, "Deleted"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> search(@RequestParam(required = false) String code,
                                    @RequestParam(required = false) String name,
                                    @RequestParam("page") int page,
                                    @RequestParam("size") int size) {
        Search s = new Search();
        s.setCode(code);
        s.setName(name);
        return new ResponseEntity<>(new SuccessResponsePage(1, projectService.count(s), projectService.search(s, page, size)), HttpStatus.OK);
    }

    @GetMapping("/searchCriteria")
    public ResponseEntity<?> searchCriteria(@RequestParam(required = false) String code,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) Long teamSize) {
        return ResponseEntity.ok(projectService.findByCodeAndNameAndTeamSizeCriteria(code, name, teamSize));
    }
}
