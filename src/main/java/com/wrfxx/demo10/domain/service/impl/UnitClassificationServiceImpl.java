package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.UnitClassification;
import com.wrfxx.demo10.domain.entity.UnitType;
import com.wrfxx.demo10.domain.repository.UnitClassificationRepository;
import com.wrfxx.demo10.domain.service.UnitClassificationService;
import com.wrfxx.demo10.exceptions.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UnitClassificationServiceImpl implements UnitClassificationService {

    private final UnitClassificationRepository unitClassificationRepository;
    private final ModelMapper modelMapper;

    public UnitClassificationServiceImpl(UnitClassificationRepository unitClassificationRepository, ModelMapper modelMapper) {
        this.unitClassificationRepository = unitClassificationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<UnitClassification> getAllUnitClassifications() {
        return unitClassificationRepository.findAll();
    }

    @Override
    public void createUnitClassification(UnitClassification dto) {
        unitClassificationRepository.save(dto);
    }

    @Override
    public void deleteUnitClassification(Long id) {
        UnitClassification unitClassification = unitClassificationRepository.findById(id).orElseThrow(()->
                new CustomException().builder()
                        .code("Unit with classification not found: "+id)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
        unitClassificationRepository.delete(unitClassification);
    }

    @Override
    public UnitClassification getUnitClassification(Long id) {
        return unitClassificationRepository.findById(id).orElseThrow(()->
                new CustomException().builder()
                        .code("Unit with classification id not found: "+id)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
    }
}
