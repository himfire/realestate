package com.wrfxx.demo10.domain.value.dto;

import com.wrfxx.demo10.domain.value.enumurator.ApplicationRoleUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTokenDTO {
    private Long userId;
    private String name;
    private ApplicationRoleUser role;
}
