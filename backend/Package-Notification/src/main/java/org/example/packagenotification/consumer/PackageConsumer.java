package org.example.packagenotification.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.packagenotification.services.PackageEventService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PackageConsumer {

    private PackageEventService packageEventService;

    public PackageConsumer(PackageEventService packageEventService) {
        this.packageEventService = packageEventService;
    }

    @KafkaListener(topics = {"packages", "sorting", "transport", "delivery"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) throws JsonProcessingException {
        packageEventService.process(message);
    }
}
