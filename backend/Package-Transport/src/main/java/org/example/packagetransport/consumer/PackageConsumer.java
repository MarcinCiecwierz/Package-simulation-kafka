package org.example.packagetransport.consumer;

import org.example.packagetransport.model.PackageSortedEvent;
import org.example.packagetransport.service.PackageTransportService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PackageConsumer {

    private PackageTransportService packageTransportService;

    public PackageConsumer(PackageTransportService packageTransportService) {
        this.packageTransportService = packageTransportService;
    }

    @KafkaListener(topics = "sorting", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(PackageSortedEvent event) throws Exception {
        packageTransportService.process(event);
    }
}
