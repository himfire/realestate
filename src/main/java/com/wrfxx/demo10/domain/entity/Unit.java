package com.wrfxx.demo10.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true)
        private String unitNo;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "unit_type_id",nullable = false)
        @JsonIgnoreProperties({"units"})
        private UnitType unitType;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "unit_classification_id",nullable = false)
        @JsonIgnoreProperties({"units"})
        private UnitClassification unitClassification;
        private Long price;
        private Long noOfFloors;
        private Long cost;
        private Long unitSize;
        private Long buildArea;
        private Long noOfBedrooms;
        private Long noOfBathrooms;

        @Lob
        private String facilities;

        @Lob
        private String description;

        private String address;
        private String unitPlanURL;

        @ElementCollection
        private List<String> unitImagesURL;

        private boolean isAvailable;

        @ManyToOne(fetch = FetchType.LAZY)
       /* @JsonIgnoreProperties({"projectType", "classification", "projectStatus"
                , "projectStatus" , "units" ,"projectAddress" ,"projectDetails" ,
                "projectInternal" , "projectExternal" , "rating", "adElements","description_ar",
                "description_en",
        })*/
    @JsonIgnore
        private Project project;
}
