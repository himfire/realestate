package com.wrfxx.demo10.domain.value.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectDTO {

    private String title;
    private String slugTitle;
    private Long type;
    private Long classification;

}
