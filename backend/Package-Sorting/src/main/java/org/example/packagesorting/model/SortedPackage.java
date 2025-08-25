package org.example.packagesorting.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity
public class SortedPackage {

    @Id
    private UUID id;
    private String sortingCenter;
    private LocalDateTime timestamp = LocalDateTime.now();
}
