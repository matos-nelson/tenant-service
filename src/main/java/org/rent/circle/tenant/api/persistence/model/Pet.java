package org.rent.circle.tenant.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.tenant.api.persistence.BaseModel;

@Entity
@Table(name = "tenant_pet")
@Setter
@Getter
@ToString
public class Pet extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "breed")
    private String breed;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "age")
    private Byte age;

    @Column(name = "tenant_id", insertable = false, updatable = false, nullable = false)
    private Long tenantId;
}
