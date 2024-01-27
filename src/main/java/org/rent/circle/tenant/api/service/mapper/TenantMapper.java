package org.rent.circle.tenant.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.tenant.api.dto.SaveTenantInfoDto;
import org.rent.circle.tenant.api.dto.TenantDto;
import org.rent.circle.tenant.api.dto.UpdateTenantDto;
import org.rent.circle.tenant.api.persistence.model.Tenant;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface TenantMapper {

    Tenant toModel(SaveTenantInfoDto saveTenantInfo);

    void update(UpdateTenantDto updateTenantInfo, @MappingTarget Tenant tenant);

    TenantDto toDto(Tenant tenant);
}
