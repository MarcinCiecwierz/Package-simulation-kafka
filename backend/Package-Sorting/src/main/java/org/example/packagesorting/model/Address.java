package org.example.packagesorting.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@ToString
public class Address {
    private String street;
    private String streetNumber;
    private String city;
    private String code;
}