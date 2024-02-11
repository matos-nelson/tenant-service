package org.rent.circle.tenant.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.tenant.api.dto.SaveTenantInfoDto;
import org.rent.circle.tenant.api.dto.TenantDto;
import org.rent.circle.tenant.api.dto.UpdateTenantDto;
import org.rent.circle.tenant.api.persistence.model.Tenant;
import org.rent.circle.tenant.api.persistence.repository.TenantRepository;
import org.rent.circle.tenant.api.service.mapper.TenantMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class TenantService {

    @Inject
    TenantRepository tenantRepository;

    @Inject
    TenantMapper tenantMapper;

    @Transactional
    public Long saveTenantInfo(SaveTenantInfoDto saveTenantInfo, String managerId) {

        Tenant tenant = tenantMapper.toModel(saveTenantInfo);
        tenant.setManagerId(managerId);

        tenantRepository.persist(tenant);
        return tenant.getId();
    }

    public TenantDto getTenant(long id, String managerId) {
        Tenant tenant = tenantRepository.findByIdAndManagerId(id, managerId);
        return tenantMapper.toDto(tenant);
    }

    @Transactional
    public void updateTenantInfo(Long tenantId, String managerId, UpdateTenantDto updateTenantInfo) {
        Tenant tenant = tenantRepository.findByIdAndManagerId(tenantId, managerId);
        if (tenant == null) {
            log.info("Could Not Find Tenant For Update");
            return;
        }

        tenantMapper.update(updateTenantInfo, tenant);
        tenantRepository.persist(tenant);
    }
}
