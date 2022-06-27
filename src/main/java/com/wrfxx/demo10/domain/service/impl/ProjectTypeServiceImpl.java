package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.Project;
import com.wrfxx.demo10.domain.entity.ProjectType;
import com.wrfxx.demo10.domain.repository.ProjectTypeRepository;
import com.wrfxx.demo10.domain.service.ProjectTypeService;
import com.wrfxx.demo10.domain.value.dto.CreateProjectDTO;
import com.wrfxx.demo10.domain.value.dto.ProjectDTO;
import com.wrfxx.demo10.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectTypeServiceImpl implements ProjectTypeService {

    private final ProjectTypeRepository projectTypeRepository;

    public ProjectTypeServiceImpl(ProjectTypeRepository projectTypeRepository) {
        this.projectTypeRepository = projectTypeRepository;
    }

    @Override
    public List<ProjectType> getAllProjectsType() {
        return projectTypeRepository.findAll();
    }

    @Override
    public void createProjectType(ProjectType dto) {
        projectTypeRepository.save(dto);
    }

    @Override
    public void deleteProjectType(Long id) {
        ProjectType projectType = projectTypeRepository.findById(id).orElseThrow(()->
                new CustomException().builder()
                        .code("Project with type not found: "+id)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
        projectTypeRepository.delete(projectType);
    }

    @Override
    public ProjectType getProjectTypeById(Long id) {
        return projectTypeRepository.findById(id).orElseThrow(()->
                new CustomException().builder()
                        .code("Project with type not found: "+id)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }

}
