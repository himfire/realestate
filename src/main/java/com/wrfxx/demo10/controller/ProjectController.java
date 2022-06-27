package com.wrfxx.demo10.controller;

import com.wrfxx.demo10.domain.entity.Project;
import com.wrfxx.demo10.domain.entity.ProjectClassification;
import com.wrfxx.demo10.domain.entity.ProjectType;
import com.wrfxx.demo10.domain.service.ProjectClassificationService;
import com.wrfxx.demo10.domain.service.ProjectService;
import com.wrfxx.demo10.domain.service.ProjectTypeService;
import com.wrfxx.demo10.domain.value.dto.ProjectDTO;
import com.wrfxx.demo10.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    private final ProjectTypeService projectTypeService;
    private final ProjectClassificationService projectClassificationService;

    public ProjectController(ProjectService projectService, ProjectTypeService projectTypeService, ProjectClassificationService projectClassificationService) {
        this.projectService = projectService;
        this.projectTypeService = projectTypeService;
        this.projectClassificationService = projectClassificationService;
    }

    @GetMapping("/{id}/id")
    public ResponseEntity getProject(@PathVariable Long id){
        return new ResponseEntity(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping("/{slugTitle}")
    public ResponseEntity getUnitByProjectSlugTitle(@PathVariable String slugTitle){
        return new ResponseEntity(projectService.getProjectSlugTitle(slugTitle), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody Project project){
        System.out.println("Form Details: "+project.toString());
        ProjectDTO result = projectService.createProject(project);
        return new ResponseEntity<ProjectDTO>(result,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO dto,@PathVariable Long id){
        ProjectDTO result = projectService.updateProject(dto,id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/type/all")
    public ResponseEntity<List<ProjectType>> getAllProjectType(){
        return new ResponseEntity<>(projectTypeService.getAllProjectsType(),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects(Pageable pageable){
        Page<Project> page = projectService.getAllProjects(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> search(@RequestParam String searchText,Pageable pageable){
        Page<Project> page = projectService.search(searchText, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/classification/all")
    public ResponseEntity<List<ProjectClassification>> getAllProjectsClassification(){
        return new ResponseEntity<>(
                projectClassificationService.getAllProjectsClassification(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAllProjects(@PathVariable Long id){
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
/*
    @PostMapping("/type")
    public ResponseEntity<String> createProjectType(@RequestBody ProjectType dto){
        projectTypeService.createProjectType(dto);
        return new ResponseEntity<>("Project Type Successfully Created",
                HttpStatus.OK);
    }
    @PostMapping("/type")
    public ResponseEntity<String> createProjectClassification(@RequestBody ProjectClassification dto){
        projectClassificationService.createProjectClassification(dto);
        return new ResponseEntity<>("Project Classification Successfully Created",
                HttpStatus.OK);
    }
*/

}
