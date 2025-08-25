package com.example.packageservice.models.dto.postPackage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private String street;
    private String streetNumber;
    private String city;
    private String code;
}
