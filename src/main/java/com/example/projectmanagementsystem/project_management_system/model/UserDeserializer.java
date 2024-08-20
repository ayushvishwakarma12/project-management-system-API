package com.example.projectmanagementsystem.project_management_system.model;

import com.example.projectmanagementsystem.project_management_system.model.User;
import com.example.projectmanagementsystem.project_management_system.repository.UserJpaRepository;
import com.example.projectmanagementsystem.project_management_system.repository.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class UserDeserializer extends JsonDeserializer<User> {

    @Autowired
    private UserJpaRepository userJpaRepository;  // Your repository to fetch User

    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//        JsonNode node = p.getCodec().readTree(p);
//        String uuidString = node.get("id").asText();
//        UUID uuid = UUID.fromString(uuidString);

        String uuidString = p.getText();
        UUID uuid = UUID.fromString(uuidString);
        return userJpaRepository.findById(uuid).orElse(null);  // Fetch the User by UUID
    }
}
