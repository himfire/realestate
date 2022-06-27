package com.wrfxx.demo10.domain.value.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wrfxx.demo10.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;
    private String title_ar;
    private String title_en;
    private String slugTitle;
    private String shortDescription_ar;
    private String shortDescription_en;
    private ProjectType projectType;
    private ProjectClassification classification;

    private ProjectStatus projectStatus;

    private String resourceUrl;
    private String mapUrl;
    private String videoUrl;
    private String mainImageUrl;

    private User user;

    private List<String> imagesUrl;

    //@Lob
    private String description_ar;

    //@Lob
    private String description_en;

    private Reservation reservation;

    private List<Unit> units;

    @Embedded
    private ProjectAddress projectAddress;

    @Embedded
    private ProjectDetails projectDetails;

    @Embedded
    private ProjectInternal projectInternal;

    @Embedded
    private ProjectExternal projectExternal;

    @Embedded
    private AdElements adElements;

    private Rating rating;
}
