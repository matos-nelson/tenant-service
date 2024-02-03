package org.rent.circle.tenant.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.Collections;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.rent.circle.tenant.api.annotation.AuthUser;
import org.rent.circle.tenant.api.dto.OccupantDto;
import org.rent.circle.tenant.api.dto.PetDto;
import org.rent.circle.tenant.api.dto.SaveTenantInfoDto;
import org.rent.circle.tenant.api.dto.UpdateTenantDto;
import org.rent.circle.tenant.api.dto.VehicleDto;

@QuarkusTest
@TestHTTPEndpoint(TenantResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@AuthUser
public class TenantResourceTest {

    @Test
    public void Post_WhenGivenAValidRequestToSave_ShouldReturnSavedTenantId() {
        // Arrange
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenseNumber("123-ABC")
            .build();
        PetDto pet = PetDto.builder()
            .name("Dog")
            .breed("Boxer")
            .weight(1)
            .age((byte) 0)
            .build();
        OccupantDto occupant = OccupantDto.builder()
            .firstName("First")
            .lastName("Last")
            .dateOfBirth(LocalDate.now())
            .build();
        SaveTenantInfoDto saveTenantInfoDto = SaveTenantInfoDto.builder()
            .propertyId(1L)
            .userId("123")
            .preferredName("Preferred Name")
            .fullName("Simple Test")
            .email("simpletest@email.com")
            .phone("1234567890")
            .vehicles(Collections.singletonList(vehicle))
            .pets(Collections.singletonList(pet))
            .occupants(Collections.singletonList(occupant))
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveTenantInfoDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void Post_WhenGivenAnInValidRequestToSave_ShouldReturnBadRequest() {
        // Arrange
        SaveTenantInfoDto saveTenantInfoDto = SaveTenantInfoDto.builder()
            .propertyId(1L)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveTenantInfoDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void GET_WhenTenantCantBeFoundById_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/1432")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GET_WhenTenantIsFoundById_ShouldReturnTenant() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is(100),
                "propertyId", is(1),
                "fullName", is("First Tenant"),
                "email", is("firsttenant@email.com"),
                "phone", is("1234445555"),
                "vehicles", is(Matchers.hasSize(1)),
                "vehicles[0].make", is("Nissan"),
                "vehicles[0].model", is("Rogue"),
                "vehicles[0].year", is(2000),
                "vehicles[0].color", is("Blue"),
                "vehicles[0].licenseNumber", is("AAA-123"),
                "pets", is(Matchers.hasSize(1)),
                "pets[0].name", is("Dog"),
                "pets[0].breed", is("Boxer"),
                "pets[0].weight", is(15),
                "pets[0].age", is(2),
                "occupants", is(Matchers.hasSize(1)),
                "occupants[0].firstName", is("First"),
                "occupants[0].lastName", is("Occupant"),
                "occupants[0].dateOfBirth", is("2010-10-10"));

    }

    @Test
    public void GET_WhenTenantCantBeFoundByEmail_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/email/notfound@email.com")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GET_WhenTenantIsFoundByEmail_ShouldReturnTenant() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/email/firsttenant@email.com")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is(100),
                "propertyId", is(1),
                "fullName", is("First Tenant"),
                "email", is("firsttenant@email.com"),
                "phone", is("1234445555"),
                "vehicles", is(Matchers.hasSize(1)),
                "vehicles[0].make", is("Nissan"),
                "vehicles[0].model", is("Rogue"),
                "vehicles[0].year", is(2000),
                "vehicles[0].color", is("Blue"),
                "vehicles[0].licenseNumber", is("AAA-123"),
                "pets", is(Matchers.hasSize(1)),
                "pets[0].name", is("Dog"),
                "pets[0].breed", is("Boxer"),
                "pets[0].weight", is(15),
                "pets[0].age", is(2),
                "occupants", is(Matchers.hasSize(1)),
                "occupants[0].firstName", is("First"),
                "occupants[0].lastName", is("Occupant"),
                "occupants[0].dateOfBirth", is("2010-10-10"));
    }

    @Test
    @UpdateTenantUser
    public void PATCH_WhenGivenRequestToUpdateTenantFailsValidation_ShouldReturnBadRequest() {
        // Arrange
        UpdateTenantDto updateTenantDto = UpdateTenantDto.builder()
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateTenantDto)
            .when()
            .patch()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @UpdateTenantUser
    public void PATCH_WhenGivenAValidRequestToUpdateTenant_ShouldReturnOk() {
        // Arrange
        long tenantId = 300L;
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenseNumber("123-ABC")
            .build();
        UpdateTenantDto updateTenantDto = UpdateTenantDto.builder()
            .phone("9999999999")
            .preferredName("New Name")
            .vehicles(Collections.singletonList(vehicle))
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateTenantDto)
            .when()
            .patch()
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);

        given()
            .when()
            .get("/" + tenantId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is(300),
                "propertyId", is(1909),
                "fullName", is("Update Tenant"),
                "email", is("updatetenant@email.com"),
                "phone", is(updateTenantDto.getPhone()),
                "preferredName", is(updateTenantDto.getPreferredName()),
                "vehicles", is(Matchers.hasSize(1)),
                "vehicles[0].make", is(vehicle.getMake()),
                "vehicles[0].model", is(vehicle.getModel()),
                "vehicles[0].year", is(vehicle.getYear()),
                "vehicles[0].color", is(vehicle.getColor()),
                "vehicles[0].licenseNumber", is(vehicle.getLicenseNumber()));
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @TestSecurity(user = "update_user")
    @JwtSecurity(claims = {
        @Claim(key = "user_id", value = "123456")
    })
    public @interface UpdateTenantUser {

    }
}
