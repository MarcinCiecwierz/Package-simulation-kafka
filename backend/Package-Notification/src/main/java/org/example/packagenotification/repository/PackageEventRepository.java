package org.example.packagenotification.repository;

import org.example.packagenotification.model.PackageEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PackageEventRepository extends JpaRepository<PackageEvent, UUID> {
    List<PackageEvent> findAllByPackageId(UUID packageId);
}
