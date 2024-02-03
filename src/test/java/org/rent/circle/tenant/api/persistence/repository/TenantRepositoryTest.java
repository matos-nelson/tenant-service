package org.rent.circle.tenant.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.tenant.api.persistence.model.Tenant;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class TenantRepositoryTest {

    @Inject
    TenantRepository tenantRepository;

    @Test
    @TestTransaction
    public void findTenantByIdAndManagerId_WhenTenantDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Tenant result = tenantRepository.findByIdAndManagerId(100L, "invalid_user");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findTenantByIdAndManagerId_WhenTenantDoesExist_ShouldReturnTenant() {
        // Arrange

        // Act
        Tenant result = tenantRepository.findByIdAndManagerId(100L, "auth_user");

        // Assert
        assertNotNull(result);
    }
}
