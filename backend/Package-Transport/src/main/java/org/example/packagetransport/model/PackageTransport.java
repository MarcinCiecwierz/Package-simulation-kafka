package org.example.packagetransport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PackageTransport {

    @Id
    private UUID id;
    private String courier;
    private String fromPlace;
    private Address address;
    private LocalDateTime timestamp;
}
