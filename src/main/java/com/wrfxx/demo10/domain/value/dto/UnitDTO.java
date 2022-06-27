package com.wrfxx.demo10.domain.value.dto;


import com.wrfxx.demo10.domain.entity.Project;
import com.wrfxx.demo10.domain.entity.UnitClassification;
import com.wrfxx.demo10.domain.entity.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitDTO {

    private Long id;
    private String unitNo;
    private UnitType unitType;
    private UnitClassification unitClassification;
    private Long price;
    private Long noOfFloors;
    private Long cost;
    private Long unitSize;
    private Long buildArea;
    private Long noOfBedrooms;
    private Long noOfBathrooms;
   /* @Lob
    private String facilities;
    @Lob
    private String description;*/
    private String address;
    private String unitPlanURL;
    private List<String> unitImagesURL;
    private boolean isAvailable;
    private ProjectDTO project;
}
