package com.example.security.mapper;

import com.example.security.models.dto.response.AppPermissionDTO;
import com.example.security.models.dto.response.AppRoleDTO;
import com.example.security.models.dto.response.AppUserDTO;
import com.example.security.models.entity.AppPermission;
import com.example.security.models.entity.AppRole;
import com.example.security.models.entity.AppUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDTO toDTO(AppUser user);

    AppRoleDTO toRoleDTO(AppRole role);

    List<AppRoleDTO> toRoleDTOs(List<AppRole> roles);

    AppPermissionDTO toPermissionDTO(AppPermission permission);

    List<AppPermissionDTO> toPermissionDTOs(List<AppPermission> permissions);

    AppUser toEntity(AppUserDTO userDTO);
}
