package org.example.packagetransport.service;

import lombok.extern.slf4j.Slf4j;
import org.example.packagetransport.model.PackageTransport;
import org.example.packagetransport.model.PackageSortedEvent;
import org.example.packagetransport.model.PackageTransportEvent;
import org.example.packagetransport.producer.PackageProducer;
import org.example.packagetransport.repository.PackageTransportRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PackageTransportService {

    private PackageTransportRepository packageTransportRepository;
    private PackageProducer packageProducer;

    public PackageTransportService(PackageTransportRepository packageTransportRepository, PackageProducer packageProducer) {
        this.packageTransportRepository = packageTransportRepository;
        this.packageProducer = packageProducer;
    }

    public void process(PackageSortedEvent event) {
        PackageTransport packageTransport = new PackageTransport();

        String courier = GetRandomCourier.getCourier();

        packageTransport.setId(event.getPackageId());
        packageTransport.setFromPlace(event.getSortingCenter());
        packageTransport.setAddress(event.getAddress());
        packageTransport.setCourier(courier);

        packageTransportRepository.save(packageTransport);

        log.info("Created package transport {}", packageTransport);

        PackageTransportEvent packageTransportEvent = new PackageTransportEvent();
        packageTransportEvent.setPackageId(event.getPackageId());
        packageTransportEvent.setAddress(event.getAddress());
        packageTransportEvent.setFrom(event.getSortingCenter());
        packageTransportEvent.setCourier(courier);

        packageProducer.send(packageTransportEvent);

    }

}
