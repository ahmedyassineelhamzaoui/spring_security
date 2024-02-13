package com.example.security.models.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AppRoleDTO {

    private String name;
    private List<AppPermissionDTO> permissions;
}
