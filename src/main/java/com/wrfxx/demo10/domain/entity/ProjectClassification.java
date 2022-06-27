package com.wrfxx.demo10.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //@JsonIgnoreProperties({"projectType","ProjectClassification","user","imagesUrl","reservation","units"})
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classification")
    private Set<Project> project;
}
