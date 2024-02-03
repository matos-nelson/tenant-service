package org.rent.circle.tenant.api.dto;

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
public class TenantDto {

    private Long id;
    private Long propertyId;
    private String preferredName;
    private String fullName;
    private String email;
    private String phone;
    private List<VehicleDto> vehicles;
    private List<PetDto> pets;
    private List<OccupantDto> occupants;
}
