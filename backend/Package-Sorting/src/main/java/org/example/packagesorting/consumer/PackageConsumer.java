package org.example.packagesorting.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.packagesorting.model.PackageCreatedEvent;
import org.example.packagesorting.service.PackageSortingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component

public class PackageConsumer {

    private PackageSortingService packageSortingService;

    public PackageConsumer(PackageSortingService packageSortingService) {
        this.packageSortingService = packageSortingService;
    }

    @KafkaListener(topics = "packages", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(PackageCreatedEvent event) throws Exception {
        packageSortingService.consume(event);
    }
}
