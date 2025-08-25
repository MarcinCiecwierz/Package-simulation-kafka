package org.example.packagenotification.web;

import org.example.packagenotification.services.PackageHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/package")
public class PackageEventController {

    private PackageHistoryService packageHistoryService;

    public PackageEventController(PackageHistoryService packageHistoryService) {
        this.packageHistoryService = packageHistoryService;
    }

    @GetMapping
    public ResponseEntity<?> getHistoryOfPackage(@RequestParam UUID packageId) {
        return ResponseEntity.ok(packageHistoryService.getHistory(packageId));
    }
}
