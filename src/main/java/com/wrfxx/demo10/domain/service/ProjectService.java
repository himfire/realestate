package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.Project;
import com.wrfxx.demo10.domain.value.dto.CreateProjectDTO;
import com.wrfxx.demo10.domain.value.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    Page<Project> getAllProjects(Pageable pageable );
    ProjectDTO createProject(Project project);
    void deleteProject(Long projectId);
    ProjectDTO getProject(Long projectId);
    Project getProjectById(Long projectId);

    ProjectDTO getProjectSlugTitle(String slugTitle);
    ProjectDTO updateProject(ProjectDTO dto, Long id);

    Page<Project> search(String searchText, Pageable pageable);
}
