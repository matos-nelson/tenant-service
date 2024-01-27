package org.rent.circle.tenant.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.tenant.api.persistence.model.Tenant;

@ApplicationScoped
public class TenantRepository implements PanacheRepository<Tenant> {

    public Tenant findByEmail(String email) {
        Parameters queryParams = Parameters.with("email", email);
        return find("email = :email", queryParams)
            .singleResultOptional()
            .orElse(null);
    }

    public Tenant findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }

    public Tenant findByIdAndManagerId(Long id, String managerId) {
        Parameters queryParams = Parameters.with("id", id).and("managerId", managerId);
        return find("id = :id and managerId = :managerId", queryParams).firstResult();
    }
}
