package org.example.packagetransport.repository;

import org.example.packagetransport.model.PackageTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageTransportRepository extends JpaRepository<PackageTransport, UUID> {
}
