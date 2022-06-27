package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.ProjectType;
import com.wrfxx.demo10.domain.entity.UnitType;

import java.util.List;

public interface UnitTypeService {
    List<UnitType> getAllUnitTypes();
    void createUnitType(UnitType dto);
    void deleteUnitType(Long id);
    UnitType getUnitTypeById(Long id);
}
