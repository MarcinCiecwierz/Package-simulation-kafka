package org.example.packagenotification.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.packagenotification.model.PackageEvent;
import org.example.packagenotification.repository.PackageEventRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PackageEventService {

    private PackageEventRepository packageEventRepository;
    private ObjectMapper objectMapper;

    public PackageEventService(PackageEventRepository packageEventRepository, ObjectMapper objectMapper) {
        this.packageEventRepository = packageEventRepository;
        this.objectMapper = objectMapper;
    }

    public void process(String message) throws JsonProcessingException {
        JsonNode node = objectMapper.readTree(message);

        System.out.println(message);

        PackageEvent packageEvent = new PackageEvent();
        packageEvent.setEventType(node.get("eventType").asText());
        packageEvent.setPackageId(UUID.fromString(node.get("packageId").asText()));
        packageEvent.setPayload(message);

        packageEventRepository.save(packageEvent);
    }
}
