package com.informationmanagement.demo.controller;

import com.informationmanagement.demo.dto.response.Message;
import com.informationmanagement.demo.dto.response.ProjectDTO;
import com.informationmanagement.demo.dto.request.Search;
import com.informationmanagement.demo.dto.response.SuccessResponse;
import com.informationmanagement.demo.dto.response.SuccessResponsePage;
import com.informationmanagement.demo.service.IProjectService;
import lombok.AllArgsConstructor;
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

    //@todo hiển thị tổng số bản ghi
    @GetMapping
    public ResponseEntity<?> search(@RequestParam(required = false) String code,
                                                    @RequestParam(required = false) String name,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size, Pageable pageable) {
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(5);
//        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Search s = new Search();

        s.setCode(code);
        s.setName(name);
        return new ResponseEntity<>(new SuccessResponsePage(1, projectService.search(s,page, size , pageable)), HttpStatus.OK);
    }
}
