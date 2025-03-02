package org.rgz_ttt.juniortask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.rgz_ttt.juniortask.dto.SubscriptionsDto;
import org.rgz_ttt.juniortask.model.Subscriptions;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionsMapper {
    @Mapping(source = "userId", target = "user.id")
    Subscriptions toEntity(SubscriptionsDto subscriptionsDto);

    @Mapping(source = "user.id", target = "userId")
    SubscriptionsDto toDto(Subscriptions subscriptions);
}