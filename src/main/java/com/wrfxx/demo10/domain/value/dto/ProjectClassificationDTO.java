package com.wrfxx.demo10.domain.value.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectClassificationDTO {
    private Long id;
    private String name;
}
