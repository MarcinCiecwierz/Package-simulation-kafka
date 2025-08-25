package org.example.packagetransport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PackageSortedEvent {
    private String eventType;
    private UUID packageId;
    private String sortingCenter;
    private Address address;
    private LocalDateTime timestamp;
}

