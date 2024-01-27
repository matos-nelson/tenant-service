package org.rent.circle.tenant.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.tenant.api.dto.SaveTenantInfoDto;
import org.rent.circle.tenant.api.dto.TenantDto;
import org.rent.circle.tenant.api.dto.UpdateTenantDto;
import org.rent.circle.tenant.api.dto.VehicleDto;
import org.rent.circle.tenant.api.persistence.model.Tenant;
import org.rent.circle.tenant.api.persistence.model.Vehicle;

@QuarkusTest
public class TenantMapperTest {

    @Inject
    TenantMapper tenantMapper;

    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        Tenant result = tenantMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenASaveTenantInfoDto_ShouldMap() {
        // Arrange
        SaveTenantInfoDto saveTenantInfo = SaveTenantInfoDto.builder()
            .propertyId(1L)
            .userId("123")
            .preferredName("Preferred Name")
            .fullName("Simple Test")
            .email("simpletest@email.com")
            .phone("1234567890")
            .build();

        // Act
        Tenant result = tenantMapper.toModel(saveTenantInfo);

        // Assert
        assertNotNull(result);
        assertEquals(saveTenantInfo.getPropertyId(), result.getPropertyId());
        assertEquals(saveTenantInfo.getUserId(), result.getUserId());
        assertEquals(saveTenantInfo.getPreferredName(), result.getPreferredName());
        assertEquals(saveTenantInfo.getFullName(), result.getFullName());
        assertEquals(saveTenantInfo.getEmail(), result.getEmail());
        assertEquals(saveTenantInfo.getPhone(), result.getPhone());
    }

    @Test
    public void toModel_WhenGivenASaveTenantInfoDtoWithVehicles_ShouldMap() {
        // Arrange
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenseNumber("123-ABC")
            .build();
        SaveTenantInfoDto saveTenantInfo = SaveTenantInfoDto.builder()
            .vehicles(Collections.singletonList(vehicle))
            .build();

        // Act
        Tenant result = tenantMapper.toModel(saveTenantInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getVehicles().size());
        assertEquals(vehicle.getMake(), result.getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenseNumber(), result.getVehicles().get(0).getLicenseNumber());
    }

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        TenantDto result = tenantMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenATenant_ShouldMap() {
        // Arrange
        Tenant tenant = new Tenant();
        tenant.setPropertyId(1L);
        tenant.setPreferredName("Preferred Name");
        tenant.setFullName("Simple Test");
        tenant.setEmail("simpletest@email.com");
        tenant.setPhone("1234567890");

        // Act
        TenantDto result = tenantMapper.toDto(tenant);

        // Assert
        assertNotNull(result);
        assertEquals(tenant.getId(), result.getId());
        assertEquals(tenant.getPropertyId(), result.getPropertyId());
        assertEquals(tenant.getPreferredName(), result.getPreferredName());
        assertEquals(tenant.getFullName(), result.getFullName());
        assertEquals(tenant.getEmail(), result.getEmail());
        assertEquals(tenant.getPhone(), result.getPhone());
    }

    @Test
    public void toDto_WhenGivenATenantWithVehicles_ShouldMap() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Make");
        vehicle.setModel("Model");
        vehicle.setYear(1000);
        vehicle.setColor("Color");
        vehicle.setLicenseNumber("123-ABC");

        Tenant tenant = new Tenant();
        tenant.setVehicles(Collections.singletonList(vehicle));

        // Act
        TenantDto result = tenantMapper.toDto(tenant);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getVehicles().size());
        assertEquals(vehicle.getMake(), result.getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenseNumber(), result.getVehicles().get(0).getLicenseNumber());
    }

    @Test
    public void update_WhenGivenNullUpdateTenantDto_ShouldReturnNull() {
        // Arrange
        Tenant tenant = new Tenant();
        tenant.setPropertyId(1L);
        tenant.setPreferredName("Preferred Name");
        tenant.setFullName("Simple Test");
        tenant.setEmail("simpletest@email.com");
        tenant.setPhone("1234567890");

        // Act
        tenantMapper.update(null, tenant);

        // Assert
        assertNotNull(tenant);
    }

    @Test
    public void update_WhenGivenAnUpdateTenantDto_ShouldMap() {
        // Arrange
        Tenant tenant = new Tenant();
        tenant.setPropertyId(1L);
        tenant.setPreferredName("Preferred Name");
        tenant.setFullName("Simple Test");
        tenant.setEmail("simpletest@email.com");
        tenant.setPhone("1234567890");

        UpdateTenantDto updateTenant = UpdateTenantDto.builder()
            .preferredName("Updated Name")
            .phone("9876543210")
            .build();

        // Act
        tenantMapper.update(updateTenant, tenant);

        // Assert
        assertNotNull(tenant);
        assertEquals(updateTenant.getPreferredName(), tenant.getPreferredName());
        assertEquals("Simple Test", tenant.getFullName());
        assertEquals("simpletest@email.com", tenant.getEmail());
        assertEquals(updateTenant.getPhone(), tenant.getPhone());
    }

    @Test
    public void update_WhenGivenAnUpdateTenantDtoWithVehicles_ShouldMap() {
        // Arrange
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenseNumber("123-ABC")
            .build();

        UpdateTenantDto updateTenantDto = UpdateTenantDto.builder()
            .vehicles(Collections.singletonList(vehicle))
            .build();

        Tenant tenant = new Tenant();

        // Act
        tenantMapper.update(updateTenantDto, tenant);

        // Assert
        assertNotNull(tenant);
        assertEquals(1, tenant.getVehicles().size());
        assertEquals(vehicle.getMake(), tenant.getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), tenant.getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), tenant.getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), tenant.getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenseNumber(), tenant.getVehicles().get(0).getLicenseNumber());
    }

    @Test
    public void update_WhenATenantHasVehicleAndGivenUpdateTenantDtoHasNoVehicles_ShouldMap() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Make");
        vehicle.setModel("Model");
        vehicle.setYear(1000);
        vehicle.setColor("Color");
        vehicle.setLicenseNumber("123-ABC");

        Tenant tenant = new Tenant();
        tenant.setVehicles(Collections.singletonList(vehicle));

        UpdateTenantDto updateTenantDto = UpdateTenantDto.builder().build();

        // Act
        tenantMapper.update(updateTenantDto, tenant);

        // Assert
        assertNotNull(tenant);
        assertNull(tenant.getVehicles());
    }

    @Test
    public void update_WhenATenantHasMoreVehicleThanGivenUpdateTenantDtoVehicles_ShouldMap() {
        // Arrange
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setMake("Make");
        vehicle1.setModel("Model");
        vehicle1.setYear(1000);
        vehicle1.setColor("Color");
        vehicle1.setLicenseNumber("123-ABC");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setMake("Make");
        vehicle2.setModel("Model");
        vehicle2.setYear(1000);
        vehicle2.setColor("Color");
        vehicle2.setLicenseNumber("123-ABC");

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        Tenant tenant = new Tenant();
        tenant.setVehicles(vehicles);

        VehicleDto vehicleDto = VehicleDto.builder()
            .make("Updated")
            .model("Model")
            .year(2020)
            .color("Silver")
            .licenseNumber("XXX-999")
            .build();

        UpdateTenantDto updateTenantDto = UpdateTenantDto.builder()
            .vehicles(Collections.singletonList(vehicleDto))
            .build();

        // Act
        tenantMapper.update(updateTenantDto, tenant);

        // Assert
        assertNotNull(tenant);
        assertEquals(1, tenant.getVehicles().size());
        assertEquals(vehicleDto.getMake(), tenant.getVehicles().get(0).getMake());
        assertEquals(vehicleDto.getModel(), tenant.getVehicles().get(0).getModel());
        assertEquals(vehicleDto.getYear(), tenant.getVehicles().get(0).getYear());
        assertEquals(vehicleDto.getColor(), tenant.getVehicles().get(0).getColor());
        assertEquals(vehicleDto.getLicenseNumber(), tenant.getVehicles().get(0).getLicenseNumber());
    }
}
