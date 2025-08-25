package org.example.packagetransport.producer;

import org.example.packagetransport.model.PackageTransportEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PackageProducer {

    private KafkaTemplate<String, PackageTransportEvent> kafkaTemplate;

    public PackageProducer(KafkaTemplate<String, PackageTransportEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(PackageTransportEvent event) {

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        kafkaTemplate.send("transport", event);
    }
}
