package org.rent.circle.tenant.api.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.rent.circle.tenant.api.persistence.BaseModel;

@Entity
@Table(name = "tenant")
@Setter
@Getter
public class Tenant extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "preferred_name")
    private String preferredName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", nullable = false)
    private List<Vehicle> vehicles;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", nullable = false)
    private List<Pet> pets;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", nullable = false)
    private List<Occupant> occupants;
}
