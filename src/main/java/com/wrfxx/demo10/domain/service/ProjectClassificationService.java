package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.Project;
import com.wrfxx.demo10.domain.entity.ProjectClassification;
import com.wrfxx.demo10.domain.entity.ProjectType;
import com.wrfxx.demo10.domain.value.dto.CreateProjectClassificationDTO;
import com.wrfxx.demo10.domain.value.dto.CreateProjectDTO;
import com.wrfxx.demo10.domain.value.dto.ProjectClassificationDTO;
import com.wrfxx.demo10.domain.value.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProjectClassificationService {

    List<ProjectClassification> getAllProjectsClassification();
    void createProjectClassification(ProjectClassification dto);
    void deleteProjectClassification(Long id);
    ProjectClassification getProjectClassification(Long id);
}
