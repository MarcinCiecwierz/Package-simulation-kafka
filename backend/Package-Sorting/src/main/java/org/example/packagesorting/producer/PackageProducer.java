package org.example.packagesorting.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.packagesorting.model.PackageSortedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PackageProducer {

    private KafkaTemplate<String, PackageSortedEvent> kafkaTemplate;

    public PackageProducer(KafkaTemplate<String, PackageSortedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(PackageSortedEvent packageSortedEvent) {

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        kafkaTemplate.send("sorting", packageSortedEvent);
    }

}
