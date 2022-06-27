package com.wrfxx.demo10.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title_ar;
    private String title_en;
    @Column(unique = true)
    private String slugTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Income income;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_type_id",nullable = false)
    @JsonIgnoreProperties({"project"})
    private ProjectType projectType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_classification_id",nullable = true)
    private ProjectClassification classification;

    @ManyToOne
    @JoinColumn(name = "project_status_ID")
    private ProjectStatus projectStatus;

/*    @OneToMany
    private ProjectFaq projectFaq;*/

    private String resourceUrl;
    private String mapUrl;
    private String videoUrl;
    private String mainImageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> imagesUrl;

    @Lob
    private String description_ar;

    @Lob
    private String description_en;



    @OneToMany(fetch = FetchType.LAZY)
    private List<Unit> units;

    private String brochure;

    private Long brochureNoOfDownloads;

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

    @OneToOne(fetch = FetchType.LAZY)
    private Rating rating;

}
