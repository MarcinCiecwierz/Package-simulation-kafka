package com.example.packageservice.models.dto.postPackage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPackageDTO {

    private ReceiverDTO receiver;
    private SenderDTO sender;
    private int height;
    private int width;
    private int depth;
    private int weight;

}

