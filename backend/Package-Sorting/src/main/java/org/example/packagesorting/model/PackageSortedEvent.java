package org.example.packagesorting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PackageSortedEvent {
    private String eventType = "PackageSorted";
    private UUID packageId;
    private String sortingCenter;
    private Address address;
    private LocalDateTime timestamp = LocalDateTime.now();
}
