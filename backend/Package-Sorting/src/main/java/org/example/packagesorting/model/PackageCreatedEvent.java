package org.example.packagesorting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PackageCreatedEvent {
    private String eventType;
    private UUID packageId;
    private String sender;
    private String receiver;
    private Address address;
    private int height;
    private int width;
    private int depth;
    private int weight;
    private LocalDateTime timestamp;
}
