package org.example.packagedelivery.service;

import org.example.packagedelivery.model.PackageDelivery;
import org.example.packagedelivery.model.PackageDeliveryEvent;
import org.example.packagedelivery.model.PackageTransportEvent;
import org.example.packagedelivery.producer.PackageProducer;
import org.example.packagedelivery.repository.PackageDeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class PackageTransportService {

    private PackageDeliveryRepository packageDeliveryRepository;
    private PackageProducer packageProducer;

    public PackageTransportService(PackageDeliveryRepository packageDeliveryRepository, PackageProducer packageProducer) {
        this.packageDeliveryRepository = packageDeliveryRepository;
        this.packageProducer = packageProducer;
    }

    public void consume(PackageTransportEvent event) {
        PackageDelivery packageDelivery = new PackageDelivery();

        packageDelivery.setId(event.getPackageId());

        packageDeliveryRepository.save(packageDelivery);

        PackageDeliveryEvent packageDeliveryEvent = new PackageDeliveryEvent();
        packageDeliveryEvent.setPackageId(event.getPackageId());

        packageProducer.send(packageDeliveryEvent);
    }

}
