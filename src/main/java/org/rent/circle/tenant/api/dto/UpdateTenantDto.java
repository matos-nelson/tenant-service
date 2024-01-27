package org.rent.circle.tenant.api.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTenantDto {

    private String preferredName;

    @NotNull
    private String phone;

    @NotNull
    private List<VehicleDto> vehicles;
}
