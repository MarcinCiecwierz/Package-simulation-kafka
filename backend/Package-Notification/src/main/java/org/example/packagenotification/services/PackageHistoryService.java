package org.example.packagenotification.services;

import org.example.packagenotification.model.PackageEvent;
import org.example.packagenotification.repository.PackageEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PackageHistoryService {

    private PackageEventRepository packageEventRepository;

    public PackageHistoryService(PackageEventRepository packageEventRepository) {
        this.packageEventRepository = packageEventRepository;
    }

    public List<PackageEvent> getHistory(UUID packageId) {
        return packageEventRepository.findAllByPackageId(packageId);
    }

}
