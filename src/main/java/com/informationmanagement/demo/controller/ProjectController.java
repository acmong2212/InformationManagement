package com.informationmanagement.demo.controller;

import com.informationmanagement.demo.dto.response.FailedResponse;
import com.informationmanagement.demo.dto.response.Message;
import com.informationmanagement.demo.dto.response.ProjectDTO;
import com.informationmanagement.demo.dto.request.Search;
import com.informationmanagement.demo.dto.response.SuccessResponse;
import com.informationmanagement.demo.model.Project;
import com.informationmanagement.demo.service.IProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/projects")
public class ProjectController {

    private IProjectService projectService;

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project projectRequest) {
        Project project = projectService.save(projectRequest);
        ProjectDTO projectDTOResponse = modelMapper.map(project, ProjectDTO.class);
        return new ResponseEntity<>(new SuccessResponse(1, projectDTOResponse), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        if (projectService.existsById(id)) {
            ProjectDTO projectDTO = projectService.findById(id);
            return new ResponseEntity<>(new SuccessResponse(1, projectDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FailedResponse(0, "404", "Project does not exist"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Long id, @RequestBody Project projectRequest) {
        if (projectService.existsById(id)) {
            projectRequest.setId(id);
            Project p = projectService.save(projectRequest);
            ProjectDTO projectDTOResponse = modelMapper.map(p, ProjectDTO.class);
            return new ResponseEntity<>(new SuccessResponse(1, projectDTOResponse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FailedResponse(0, "404", "Project does not exist"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        if (projectService.existsById(id)) {
            projectService.deleteById(id);
            return new ResponseEntity<>(new Message(1, "Deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FailedResponse(0, "404", "Project does not exist"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProjectDTO>> search(@RequestParam(required = false) String code,
                                                    @RequestParam(required = false) String name,
                                                    @RequestParam("page") Optional<Integer> page,
                                                    @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Search s = new Search();

        s.setCode(code);
        s.setName(name);
        return new ResponseEntity<>(projectService.search(s, pageable), HttpStatus.OK);
    }
}
