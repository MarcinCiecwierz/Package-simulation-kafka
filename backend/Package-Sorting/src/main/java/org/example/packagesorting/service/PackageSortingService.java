package org.example.packagesorting.service;

import lombok.extern.slf4j.Slf4j;
import org.example.packagesorting.model.PackageCreatedEvent;
import org.example.packagesorting.model.PackageSortedEvent;
import org.example.packagesorting.model.SortedPackage;
import org.example.packagesorting.producer.PackageProducer;
import org.example.packagesorting.repository.SortedPackageRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PackageSortingService {

    private SortedPackageRepository sortedPackageRepository;
    private PackageProducer packageProducer;

    public PackageSortingService(SortedPackageRepository sortedPackageRepository, PackageProducer packageProducer) {
        this.sortedPackageRepository = sortedPackageRepository;
        this.packageProducer = packageProducer;
    }

    public void consume(PackageCreatedEvent event) {
        SortedPackage sortedPackage = new SortedPackage();

        sortedPackage.setId(event.getPackageId());
        String sortingCenter = GetSortingCenter.getSortingCenter(event.getAddress().getCode());
        sortedPackage.setSortingCenter(sortingCenter);

        sortedPackageRepository.save(sortedPackage);

        log.info("Added sorted package {}", sortedPackage);

        PackageSortedEvent packageSortedEvent = new PackageSortedEvent();
        packageSortedEvent.setPackageId(event.getPackageId());
        packageSortedEvent.setSortingCenter(sortingCenter);
        packageSortedEvent.setAddress(event.getAddress());

        packageProducer.send(packageSortedEvent);

        log.info("Sent topic {}", packageSortedEvent);
    }

}
