package org.rent.circle.tenant.api.resource;

import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.rent.circle.tenant.api.dto.SaveTenantInfoDto;
import org.rent.circle.tenant.api.dto.TenantDto;
import org.rent.circle.tenant.api.dto.UpdateTenantDto;
import org.rent.circle.tenant.api.service.TenantService;

@AllArgsConstructor
@Authenticated
@Path("/tenant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class TenantResource {

    private final TenantService tenantService;
    private final JsonWebToken jwt;

    @POST
    public Long saveTenant(@Valid SaveTenantInfoDto saveTenantInfo) {
        return tenantService.saveTenantInfo(saveTenantInfo, jwt.getName());
    }

    @GET
    @Path("/{id}")
    public TenantDto getTenant(@NotNull @PathParam("id") long tenantId) {
        return tenantService.getTenant(tenantId, jwt.getName());
    }

    @GET
    @Path("/email/{email}")
    public TenantDto getTenant(@NotNull @NotBlank @Email @PathParam("email") String tenantEmail) {
        return tenantService.getTenantByEmail(tenantEmail);
    }

    @PATCH
    public void updateTenant(@Valid UpdateTenantDto updateTenantDto) {
        tenantService.updateTenantInfo(jwt.getName(), updateTenantDto);
    }
}
