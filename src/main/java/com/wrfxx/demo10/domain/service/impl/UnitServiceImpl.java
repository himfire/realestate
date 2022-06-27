package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.*;
import com.wrfxx.demo10.domain.repository.ProjectRepository;
import com.wrfxx.demo10.domain.repository.UnitClassificationRepository;
import com.wrfxx.demo10.domain.repository.UnitRepository;
import com.wrfxx.demo10.domain.repository.UnitTypeRepository;
import com.wrfxx.demo10.domain.service.MailService;
import com.wrfxx.demo10.domain.service.UnitService;
import com.wrfxx.demo10.domain.value.dto.CreateUnitDTO;
import com.wrfxx.demo10.domain.value.dto.UnitDTO;
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
public class UnitServiceImpl implements UnitService {

    private final ProjectRepository projectRepository;
    private final UnitRepository unitRepository;
    private final ModelMapper modelMapper;
    private final MailService mailService;

    private final UnitClassificationRepository unitClassificationRepository;
    private final UnitTypeRepository unitTypeRepository;

    public UnitServiceImpl(ProjectRepository projectRepository, UnitRepository unitRepository,
                           ModelMapper modelMapper,
                           MailService mailService, UnitClassificationRepository unitClassificationRepository, UnitTypeRepository unitTypeRepository) {
        this.projectRepository = projectRepository;
        this.unitRepository = unitRepository;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
        this.unitClassificationRepository = unitClassificationRepository;
        this.unitTypeRepository = unitTypeRepository;
    }

    @Override
    public Page<Unit> getAllUnits(Pageable pageable) {
        return unitRepository.findAll(pageable);
    }

    @Override
    public Page<Unit> getUnitsByProjectId(Long projectId, Pageable pageable) {
        return unitRepository.findByProjectId(projectId, pageable);
    }

    @Override
    public Page<UnitDTO> getUnitsByProjectSlugTitle(String slugTitle, Pageable pageable) {
        Project project = projectRepository.findBySlugTitle(slugTitle).orElseThrow(
            ()-> new CustomException().builder()
                    .code("Units not found with slugTitle: "+slugTitle)
                    .status(HttpStatus.NOT_FOUND)
                    .build()
        );
        Page<Unit> units = unitRepository.findByProjectId(project.getId(), pageable);
        return units.map(unit -> modelMapper.map(unit,UnitDTO.class));
    }

    @Override
    public UnitDTO getUnitBySlugTitleAndUnitNo(String slugTitle, String unitNo) {
        Unit unit = unitRepository.findByProjectSlugTitleAndUnitNo(slugTitle,unitNo)
                .orElseThrow(()-> CustomException.builder()
                        .code("Error in finding unit: "+unitNo)
                        .status(HttpStatus.NOT_FOUND)
                        .build());

        return modelMapper.map(unit,UnitDTO.class);
    }

    @Override
    public UnitDTO updateUnitBySlugTitleAndUnitNo(String slugTitle, String unitNo) {
        Unit unit = unitRepository.findByProjectSlugTitleAndUnitNo(slugTitle,unitNo)
                .orElseThrow(
                () -> CustomException.builder()
                        .code("Unit not found with unit number: "+unitNo)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
        unitRepository.save(unit);
        return modelMapper.map(unit,UnitDTO.class);
    }

    @Override
    public UnitDTO createUnit(CreateUnitDTO dto, String slugTitle) {

        Project project = projectRepository.findBySlugTitle(slugTitle)
                .orElseThrow(
                        ()-> CustomException.builder()
                                .code("Project not found with slugTitle: "+slugTitle)
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
        Unit unit = Unit
                .builder()
                .unitNo(dto.getUnitNo())
                .unitType(dto.getUnitType())
                .unitClassification(dto.getUnitClassification())
                .project(project)
                .build();
        Unit result =  unitRepository.save(unit);
        return modelMapper.map(result, UnitDTO.class);
    }

    @Override
    public void deleteUnit(Long unitId) {
        Unit unit = unitRepository.findById(unitId).orElseThrow(
                () -> CustomException.builder()
                        .code("Unit not found with id: "+unitId)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
        unitRepository.delete(unit);
    }

    @Override
    public UnitDTO getUnit(Long unitId) {
        Unit unit = getUnitById(unitId);
        return modelMapper.map(unit,UnitDTO.class);
    }

    @Override
    public Unit getUnitById(Long unitId) {
        Unit unit = unitRepository.findById(unitId).orElseThrow(
                ()->new CustomException()
                                .builder()
                                .code("Unit not found with id: "+unitId)
                                .status(HttpStatus.NOT_FOUND)
                                .build()
        );
        return unit;
    }

    @Override
    public UnitDTO updateUnit(UnitDTO dto, Long id) {
        Unit unit = unitRepository.findById(id).orElseThrow(
                () -> CustomException.builder()
                        .code("Unit not found with id: "+id)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
        dto.setId(id);
        unitRepository.save(modelMapper.map(dto,Unit.class));
        return dto;
    }
}
