package com.wrfxx.demo10.domain.service;

import com.wrfxx.demo10.domain.entity.Unit;
import com.wrfxx.demo10.domain.value.dto.UnitDTO;
import com.wrfxx.demo10.domain.value.dto.CreateUnitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

@Transactional
public interface UnitService {
    Page<Unit> getAllUnits(Pageable pageable);
    Page<Unit> getUnitsByProjectId(Long unitId, Pageable pageable);
    Page<UnitDTO> getUnitsByProjectSlugTitle(String slugTitle,Pageable pageable);
    UnitDTO getUnitBySlugTitleAndUnitNo(String slugTitle,String unitNo);
    UnitDTO updateUnitBySlugTitleAndUnitNo(String slugTitle,String unitNo);
    UnitDTO createUnit(CreateUnitDTO unit, String slugTitle);
    void deleteUnit(Long unitId);
    UnitDTO getUnit(Long unitId);
    Unit getUnitById(Long unitId);
    UnitDTO updateUnit(UnitDTO dto, Long id);
}
