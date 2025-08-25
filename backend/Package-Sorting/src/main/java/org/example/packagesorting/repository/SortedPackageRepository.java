package org.example.packagesorting.repository;

import org.example.packagesorting.model.SortedPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SortedPackageRepository extends JpaRepository<SortedPackage, UUID> {
}
