package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.ProjectClassification;
import com.wrfxx.demo10.domain.entity.UnitClassification;

import java.util.List;

public interface UnitClassificationService {

    List<UnitClassification> getAllUnitClassifications();
    void createUnitClassification(UnitClassification dto);
    void deleteUnitClassification(Long id);
    UnitClassification getUnitClassification(Long id);
}
