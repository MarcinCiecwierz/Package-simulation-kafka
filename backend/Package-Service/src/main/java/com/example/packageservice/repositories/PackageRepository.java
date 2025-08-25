package com.example.packageservice.repositories;

import com.example.packageservice.models.packageInfo.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package, UUID> {
}
