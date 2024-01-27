package org.rent.circle.tenant.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateTenantDtoTest {

    @Test
    public void UpdateTenantDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(UpdateTenantDto.class);

        // Assert

    }
}
