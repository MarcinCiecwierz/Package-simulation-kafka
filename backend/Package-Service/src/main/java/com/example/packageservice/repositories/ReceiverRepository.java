package com.example.packageservice.repositories;

import com.example.packageservice.models.packageInfo.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReceiverRepository extends JpaRepository<Receiver, UUID> {
}
