package com.example.security.mapper;

import com.example.security.models.dto.response.AppUserDTO;
import com.example.security.models.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDTO toDTO(AppUser user);

    AppUser toEntity(AppUserDTO userDTO);

}
