package com.example.packageservice.models.dto.postPackage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiverDTO extends PersonDTO {
    private AddressDTO address;
}
