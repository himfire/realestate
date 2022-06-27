package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.ProjectClassification;
import com.wrfxx.demo10.domain.entity.ProjectType;
import com.wrfxx.demo10.domain.repository.ProjectClassificationRepository;
import com.wrfxx.demo10.domain.service.ProjectClassificationService;
import com.wrfxx.demo10.domain.value.dto.CreateProjectClassificationDTO;
import com.wrfxx.demo10.domain.value.dto.ProjectClassificationDTO;
import com.wrfxx.demo10.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@Slf4j
public class ProjectClassificationServiceImpl implements ProjectClassificationService {

    private final ProjectClassificationRepository projectClassificationRepository;
    private final ModelMapper modelMapper;

    public ProjectClassificationServiceImpl(ProjectClassificationRepository projectClassificationRepository,
                                            ModelMapper modelMapper) {
        this.projectClassificationRepository = projectClassificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProjectClassification> getAllProjectsClassification() {
        return projectClassificationRepository.findAll();
    }

    @Override
    public void createProjectClassification(ProjectClassification dto) {
        projectClassificationRepository.save(dto);
    }

    @Override
    public void deleteProjectClassification(Long id) {
        ProjectClassification projectClassification =
                projectClassificationRepository.findById(id).orElseThrow(
                        ()->new CustomException().builder()
                                        .code("Project with classification not found: "+id)
                                        .status(HttpStatus.NOT_FOUND)
                                        .build()
        );
        projectClassificationRepository.delete(projectClassification);
    }

    @Override
    public ProjectClassification getProjectClassification(Long id) {
        return projectClassificationRepository.findById(id).orElseThrow(
                ()->new CustomException().builder()
                .code("Project with classification not found: "+id)
                .status(HttpStatus.NOT_FOUND)
                .build()

        );
    }
}
