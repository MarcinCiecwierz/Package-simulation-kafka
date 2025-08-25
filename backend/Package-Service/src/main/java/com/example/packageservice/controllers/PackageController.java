package com.example.packageservice.controllers;

import com.example.packageservice.models.dto.postPackage.PostPackageDTO;
import com.example.packageservice.services.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/package")
public class PackageController {

    private PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    public ResponseEntity<?> publicPackage(@RequestBody PostPackageDTO postPackageDTO) {
        return ResponseEntity.ok(packageService.publishPackage(postPackageDTO));
    }
}
