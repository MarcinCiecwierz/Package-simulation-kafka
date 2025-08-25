package org.example.packagedelivery.repository;

import org.example.packagedelivery.model.PackageDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageDeliveryRepository extends JpaRepository<PackageDelivery, UUID> {
}
