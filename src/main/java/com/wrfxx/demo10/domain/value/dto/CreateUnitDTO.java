package com.wrfxx.demo10.domain.value.dto;

import com.wrfxx.demo10.domain.entity.UnitClassification;
import com.wrfxx.demo10.domain.entity.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUnitDTO {

    private String unitNo;
    private UnitType unitType;
    private UnitClassification unitClassification;
}
