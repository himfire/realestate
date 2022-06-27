package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.Project;
import com.wrfxx.demo10.domain.entity.ProjectType;
import com.wrfxx.demo10.domain.value.dto.CreateProjectDTO;
import com.wrfxx.demo10.domain.value.dto.ProjectDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProjectTypeService {

    List<ProjectType> getAllProjectsType();
    void createProjectType(ProjectType dto);
    void deleteProjectType(Long id);
    ProjectType getProjectTypeById(Long id);
}
