package com.wrfxx.demo10.domain.service.impl;

import com.wrfxx.demo10.domain.entity.UnitType;
import com.wrfxx.demo10.domain.repository.UnitTypeRepository;
import com.wrfxx.demo10.domain.service.UnitTypeService;
import com.wrfxx.demo10.exceptions.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class UnitTypeServiceImpl implements UnitTypeService {

    private final UnitTypeRepository unitTypeRepository;
    private final ModelMapper modelMapper;

    public UnitTypeServiceImpl(UnitTypeRepository unitTypeRepository, ModelMapper modelMapper) {
        this.unitTypeRepository = unitTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UnitType> getAllUnitTypes() {
        return unitTypeRepository.findAll();
    }

    @Override
    public void createUnitType(UnitType dto) {
        unitTypeRepository.save(dto);
    }

    @Override
    public void deleteUnitType(Long id) {
        UnitType unitType = unitTypeRepository.findById(id).orElseThrow(()->
                new CustomException().builder()
                        .code("Unit with type not found: "+id)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
        unitTypeRepository.delete(unitType);
    }

    @Override
    public UnitType getUnitTypeById(Long id) {
        return unitTypeRepository.findById(id).orElseThrow(()->
                new CustomException().builder()
                        .code("Unit with type not found: "+id)
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
    }
}
