package org.rgz_ttt.juntask.mapper;

import org.mapstruct.*;
import org.rgz_ttt.juntask.dto.UserDto;
import org.rgz_ttt.juntask.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    User updateWithNull(UserDto userDto, @MappingTarget User user);

    @AfterMapping
    default void linkSubscriptions(@MappingTarget User user) {
        user.getSubscriptions().forEach(subscription -> subscription.setUser(user));
    }
}