package com.example.packageservice.repositories;

import com.example.packageservice.models.packageInfo.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SenderRepository extends JpaRepository<Sender, UUID> {
}
