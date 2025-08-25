package com.example.packageservice.services;

import com.example.packageservice.models.dto.postPackage.PackageCreatedEvent;
import com.example.packageservice.models.dto.postPackage.PostPackageDTO;
import com.example.packageservice.models.packageInfo.*;
import com.example.packageservice.models.packageInfo.Package;
import com.example.packageservice.repositories.PackageRepository;
import com.example.packageservice.repositories.ReceiverRepository;
import com.example.packageservice.repositories.SenderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PackageService {

    private final ReceiverRepository receiverRepository;
    private KafkaTemplate<String, PackageCreatedEvent> kafkaTemplate;
    private PackageRepository packageRepository;
    private SenderRepository senderRepository;

    public PackageService(KafkaTemplate<String, PackageCreatedEvent> kafkaTemplate,
                          PackageRepository packageRepository, SenderRepository senderRepository,
                          ReceiverRepository receiverRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.packageRepository = packageRepository;
        this.senderRepository = senderRepository;
        this.receiverRepository = receiverRepository;
    }

    public UUID publishPackage(PostPackageDTO postPackageDTO) {
        System.out.println("Publishing package " + postPackageDTO);

        Receiver receiver = new Receiver();
        receiver.setFirstName(postPackageDTO.getReceiver().getFirstName());
        receiver.setLastName(postPackageDTO.getReceiver().getLastName());
        receiver.setEmail(postPackageDTO.getReceiver().getEmail());
        receiver.setPhoneNumber(postPackageDTO.getReceiver().getPhoneNumber());
        
        Address address = new Address(
                postPackageDTO.getReceiver().getAddress().getStreet(),
                postPackageDTO.getReceiver().getAddress().getStreetNumber(),
                postPackageDTO.getReceiver().getAddress().getCity(),
                postPackageDTO.getReceiver().getAddress().getCode()
        );
        
        receiver.setAddress(address);

        Sender sender = new Sender();
        sender.setFirstName(postPackageDTO.getSender().getFirstName());
        sender.setLastName(postPackageDTO.getSender().getLastName());
        sender.setEmail(postPackageDTO.getSender().getEmail());
        sender.setPhoneNumber(postPackageDTO.getSender().getPhoneNumber());

        Package newPackage = mapToPackage(postPackageDTO);

        newPackage.setReceiver(receiver);
        newPackage.setSender(sender);

        receiverRepository.save(receiver);
        senderRepository.save(sender);

        newPackage = packageRepository.save(newPackage);

        PackageCreatedEvent packageCreatedEvent = getPackageCreatedEvent(newPackage);
        packageCreatedEvent.setAddress(address);

        log.info("Publishing is being published.");

        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        kafkaTemplate.send("packages", packageCreatedEvent);

        return newPackage.getId();
    }

    private PackageCreatedEvent getPackageCreatedEvent(Package newPackage) {
        PackageCreatedEvent packageCreatedEvent = new PackageCreatedEvent();
        packageCreatedEvent.setPackageId(newPackage.getId());
        packageCreatedEvent.setHeight(newPackage.getHeight());
        packageCreatedEvent.setWidth(newPackage.getWidth());
        packageCreatedEvent.setDepth(newPackage.getDepth());
        packageCreatedEvent.setSender(newPackage.getSender().getFirstName() + " " + newPackage.getSender().getLastName());
        packageCreatedEvent.setReceiver(newPackage.getReceiver().getFirstName() + " " + newPackage.getReceiver().getLastName());
        return packageCreatedEvent;
    }

    private Package mapToPackage(PostPackageDTO postPackageDTO) {
        Package pkg = new Package();
        pkg.setDepth(postPackageDTO.getDepth());
        pkg.setHeight(postPackageDTO.getHeight());
        pkg.setWidth(postPackageDTO.getWidth());
        pkg.setWeight(postPackageDTO.getWeight());

        return pkg;
    }
}
