package com.wrfxx.demo10.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    @Lob
    private String description;
    private String resourceUrl;
    private String mapUrl;
    private Long floors;
    private Long units;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;
}
