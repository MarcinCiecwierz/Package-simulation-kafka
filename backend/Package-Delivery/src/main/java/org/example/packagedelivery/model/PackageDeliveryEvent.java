package org.example.packagedelivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageDeliveryEvent {

    private String eventType = "PackageDelivery";
    private UUID packageId;
    private LocalDateTime timestamp = LocalDateTime.now();
}
