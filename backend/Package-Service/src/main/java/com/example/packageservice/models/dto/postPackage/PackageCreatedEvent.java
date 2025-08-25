package com.example.packageservice.models.dto.postPackage;

import com.example.packageservice.models.packageInfo.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PackageCreatedEvent {
    private String eventType = "PackageCreated";
    private UUID packageId;
    private String sender;
    private String receiver;
    private Address address;
    private int height;
    private int width;
    private int depth;
    private int weight;
    private LocalDateTime timestamp = LocalDateTime.now();
}
