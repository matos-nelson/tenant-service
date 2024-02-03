package org.rent.circle.tenant.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rent.circle.tenant.api.dto.SaveTenantInfoDto;
import org.rent.circle.tenant.api.dto.TenantDto;
import org.rent.circle.tenant.api.dto.UpdateTenantDto;
import org.rent.circle.tenant.api.dto.VehicleDto;
import org.rent.circle.tenant.api.persistence.model.Tenant;
import org.rent.circle.tenant.api.persistence.repository.TenantRepository;
import org.rent.circle.tenant.api.service.mapper.TenantMapper;

@QuarkusTest
public class TenantServiceTest {

    @InjectMock
    TenantRepository tenantRepository;

    @InjectMock
    TenantMapper tenantMapper;

    @Inject
    TenantService tenantService;

    @Test
    public void saveTenantInfo_WhenCalled_ShouldReturnSavedTenantId() {
        // Arrange
        String managerId = "abc123";
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenseNumber("123-ABC")
            .build();
        SaveTenantInfoDto saveTenantInfo = SaveTenantInfoDto.builder()
            .propertyId(1L)
            .preferredName("Preferred Name")
            .fullName("Simple Test")
            .email("simpletest@email.com")
            .phone("1234567890")
            .vehicles(Collections.singletonList(vehicle))
            .build();

        Tenant tenant = new Tenant();
        tenant.setId(100L);
        when(tenantMapper.toModel(saveTenantInfo)).thenReturn(tenant);

        // Act
        Long result = tenantService.saveTenantInfo(saveTenantInfo, managerId);

        // Assert
        assertNotNull(result);
        assertEquals(tenant.getId(), result);
    }

    @Test
    public void getTenant_WhenTenantWithGivenIdCantBeFound_ShouldReturnNull() {
        // Arrange
        String managerId = "abc123";
        long tenantId = 1;
        when(tenantRepository.findByIdAndManagerId(tenantId, managerId)).thenReturn(null);
        when(tenantMapper.toDto(null)).thenReturn(null);

        // Act
        TenantDto result = tenantService.getTenant(tenantId, managerId);

        // Assert
        assertNull(result);
    }

    @Test
    public void getTenant_WhenCalled_ShouldReturnTenant() {
        // Arrange
        String managerId = "abc123";
        long tenantId = 100;

        Tenant tenant = new Tenant();
        tenant.setId(tenantId);

        TenantDto tenantDto = TenantDto.builder()
            .propertyId(1L)
            .fullName("My Tenant")
            .email("tenant@email.com")
            .phone("1234567890")
            .build();

        when(tenantRepository.findByIdAndManagerId(tenantId, managerId)).thenReturn(tenant);
        when(tenantMapper.toDto(tenant)).thenReturn(tenantDto);

        // Act
        TenantDto result = tenantService.getTenant(tenantId, managerId);

        // Assert
        assertEquals(tenantDto, result);
    }

    @Test
    public void getTenantByEmail_WhenTenantWithGivenIdCantBeFound_ShouldReturnNull() {
        // Arrange
        String tenantEmail = "tenant@email.com";
        when(tenantRepository.findByEmail(tenantEmail)).thenReturn(null);
        when(tenantMapper.toDto(null)).thenReturn(null);

        // Act
        TenantDto result = tenantService.getTenantByEmail(tenantEmail);

        // Assert
        assertNull(result);
    }

    @Test
    public void getTenantByEmail_WhenCalled_ShouldReturnTenant() {
        // Arrange
        String tenantEmail = "tenant@email.com";

        Tenant tenant = new Tenant();
        tenant.setId(100L);
        tenant.setEmail(tenantEmail);

        TenantDto tenantDto = TenantDto.builder()
            .propertyId(1L)
            .fullName("My Tenant")
            .email("tenant@email.com")
            .phone("1234567890")
            .build();

        when(tenantRepository.findByEmail(tenantEmail)).thenReturn(tenant);
        when(tenantMapper.toDto(tenant)).thenReturn(tenantDto);

        // Act
        TenantDto result = tenantService.getTenantByEmail(tenantEmail);

        // Assert
        assertEquals(tenantDto, result);
    }

    @Test
    public void updateTenant_WhenTenantIsNotFound_ShouldReturnNotUpdate() {
        // Arrange
        Long tenantId = 1L;
        String managerId = "123";
        UpdateTenantDto updateTenantDto = UpdateTenantDto.builder().build();
        when(tenantRepository.findByIdAndManagerId(tenantId, managerId)).thenReturn(null);

        // Act
        tenantService.updateTenantInfo(tenantId, managerId, updateTenantDto);

        // Assert
        verify(tenantMapper, never()).update(updateTenantDto, null);
        verify(tenantRepository, never()).persist(Mockito.any(Tenant.class));
    }

    @Test
    public void updateTenantInfo_WhenCalled_ShouldUpdate() {
        // Arrange
        Long tenantId = 1L;
        String managerId = "123";

        Tenant tenant = new Tenant();
        tenant.setId(tenantId);

        UpdateTenantDto updateTenantDto = UpdateTenantDto.builder()
            .preferredName("Updated Name")
            .phone("9876543210")
            .build();
        when(tenantRepository.findByIdAndManagerId(tenantId, managerId)).thenReturn(tenant);

        // Act
        tenantService.updateTenantInfo(tenantId, managerId, updateTenantDto);

        // Assert
        verify(tenantMapper, times(1)).update(updateTenantDto, tenant);
        verify(tenantRepository, times(1)).persist(tenant);
    }
}
