package com.wrfxx.demo10.units;

import com.wrfxx.demo10.domain.entity.Unit;
import com.wrfxx.demo10.domain.repository.UnitClassificationRepository;
import com.wrfxx.demo10.domain.repository.UnitRepository;
import com.wrfxx.demo10.domain.repository.UnitTypeRepository;
import com.wrfxx.demo10.domain.value.dto.CreateUnitDTO;
import com.wrfxx.demo10.domain.value.dto.UnitDTO;
import com.wrfxx.demo10.exceptions.CustomException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

/*@DataJpaTest*/
public class UnitRepositoryTests {
    private final ModelMapper modelMapper;
    private final UnitRepository unitRepository;
    private final UnitClassificationRepository unitClassificationRepository;
    private final UnitTypeRepository unitTypeRepository;

    public UnitRepositoryTests(ModelMapper modelMapper, UnitRepository unitRepository, UnitClassificationRepository unitClassificationRepository, UnitTypeRepository unitTypeRepository) {
        this.modelMapper = modelMapper;
        this.unitRepository = unitRepository;
        this.unitClassificationRepository = unitClassificationRepository;
        this.unitTypeRepository = unitTypeRepository;
    }

    /*public Page<Unit> getAllUnits(Pageable pageable){
        return null;
    }
    public Page<Unit> getUnitsByProjectId(Long unitId, Pageable pageable){
        return null;

    }
    public Page<UnitDTO> getUnitsByProjectSlugTitle(String slugTitle, Pageable pageable){

        return null;
    }
    public UnitDTO getUnitBySlugTitleAndUnitNo(String slugTitle,String unitNo){

        return null;
    }*/

 /*   @Test*/
    public UnitDTO testUpdateUnitBySlugTitleAndUnitNo(){
        String slugTitle = "teste-slug-title";
        String unitNo = "214";
        Unit unit = unitRepository.findByProjectSlugTitleAndUnitNo(slugTitle,unitNo)
                .orElseThrow(
                        () -> CustomException.builder()
                                .code("Unit not found with unit number: "+unitNo)
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
        Unit updatedUnit = unitRepository.save(unit);
        Assertions.assertThat(updatedUnit.getUnitSize()).isEqualTo(7);
        return modelMapper.map(unit,UnitDTO.class);
    }
    /*public UnitDTO createUnit(CreateUnitDTO unit, String slugTitle){

        return null;
    }
    public void deleteUnit(Long unitId){

    }
    public UnitDTO getUnit(Long unitId){
        return null;
    }
    public Unit getUnitById(Long unitId){
        return null;
    }
    public UnitDTO updateUnit(UnitDTO dto, Long id){
        return null;
    }*/
}
