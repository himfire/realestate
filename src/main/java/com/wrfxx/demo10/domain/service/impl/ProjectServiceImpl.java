package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.Project;
import com.wrfxx.demo10.domain.repository.ProjectClassificationRepository;
import com.wrfxx.demo10.domain.repository.ProjectRepository;
import com.wrfxx.demo10.domain.repository.ProjectTypeRepository;
import com.wrfxx.demo10.domain.repository.UserRepository;
import com.wrfxx.demo10.domain.service.MailService;
import com.wrfxx.demo10.domain.service.ProjectService;
import com.wrfxx.demo10.domain.value.dto.ProjectDTO;
import com.wrfxx.demo10.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MailService mailService;
    private final ProjectRepository projectRepository;
    private final ProjectTypeRepository projectTypeRepository;
    private final ProjectClassificationRepository projectClassificationRepository;

    public ProjectServiceImpl(UserRepository userRepository, ModelMapper modelMapper, MailService mailService, ProjectRepository projectRepository, ProjectTypeRepository projectTypeRepository, ProjectClassificationRepository projectClassificationRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
        this.projectRepository = projectRepository;
        this.projectTypeRepository = projectTypeRepository;
        this.projectClassificationRepository = projectClassificationRepository;
    }


    @Override
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public ProjectDTO createProject(Project project) {
      /*  ProjectType type = projectTypeRepository
                .findById(Long.valueOf(dto.getType()))
                .orElseThrow(
                        () -> new CustomException()
                                .builder()
                                .code("Project type not found: ")
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
        ProjectClassification classification = projectClassificationRepository
                .findById(Long.valueOf(dto.getClassification()))
                .orElseThrow(
                        () -> new CustomException()
                                .builder()
                                .code("Project Classification not found: ")
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
*/
        Project prj =  Project.builder()
                .title_ar(project.getTitle_ar())
                .title_en(project.getTitle_en())
                .slugTitle(project.getSlugTitle())
                .projectType(project.getProjectType())
                .classification(project.getClassification())

                .build();

        Project result =  projectRepository.save(project);
        return modelMapper.map(result,ProjectDTO.class);
    }

    @Override
    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> CustomException.builder()
                        .code("Project not found with id: "+projectId)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
        projectRepository.delete(project);
    }

    @Override
    public ProjectDTO getProject(Long projectId) {
        Project project = getProjectById(projectId);
        return modelMapper.map(project,ProjectDTO.class);
    }


    @Override
    public Project getProjectById(Long projectId) {

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> CustomException.builder()
                        .code("Project not found with id: "+projectId)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );

        return project;
    }

    @Override
    public ProjectDTO getProjectSlugTitle(String slugTitle) {
        Project project = projectRepository.findBySlugTitle(slugTitle).orElseThrow(
                        () -> CustomException.builder()
                                .code("Project not found with SlugTitle: "+slugTitle)
                                .status(HttpStatus.NOT_FOUND)
                                .build()

        );
        return modelMapper.map(project,ProjectDTO.class);
    }

    @Override
    public ProjectDTO updateProject(ProjectDTO dto, Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> CustomException.builder()
                        .code("Project not found with id: "+id)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
        dto.setId(id);
        projectRepository.save(modelMapper.map(dto,Project.class));
        return dto;
    }

    @Override
    public Page<Project> search(String searchText, Pageable pageable){
        return projectRepository.search(searchText, pageable);
    }
}
