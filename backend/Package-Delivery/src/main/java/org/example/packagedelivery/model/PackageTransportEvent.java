package org.example.packagedelivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageTransportEvent {

    private String eventType;
    private UUID packageId;
    private String courier;
    private String from;
    private Address address;
    private LocalDateTime timestamp;
}
