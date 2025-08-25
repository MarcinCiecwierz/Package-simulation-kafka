package org.example.packagedelivery.consumer;

import org.example.packagedelivery.model.PackageTransportEvent;
import org.example.packagedelivery.service.PackageTransportService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PackageConsumer {

    private PackageTransportService packageTransportService;

    public PackageConsumer(PackageTransportService packageTransportService) {
        this.packageTransportService = packageTransportService;
    }

    @KafkaListener(topics = "transport", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(PackageTransportEvent event) {
        packageTransportService.consume(event);
    }
}
