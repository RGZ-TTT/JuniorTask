package org.rgz_ttt.juniortask.mapper;


import org.mapstruct.*;
import org.rgz_ttt.juniortask.dto.UserDto;
import org.rgz_ttt.juniortask.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}