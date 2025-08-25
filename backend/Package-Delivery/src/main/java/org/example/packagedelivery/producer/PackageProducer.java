package org.example.packagedelivery.producer;

import org.example.packagedelivery.model.PackageDeliveryEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PackageProducer {

    private KafkaTemplate<String, PackageDeliveryEvent> kafkaTemplate;

    public PackageProducer(KafkaTemplate<String, PackageDeliveryEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(PackageDeliveryEvent event) {

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        kafkaTemplate.send("delivery", event);
    }
}
