package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class JsonConverter {
    private final ObjectMapper jsonMapper;

    public <T> String stringifyObject(T doc) {
        try {
            return jsonMapper.writeValueAsString(doc);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialized policy to json", e);
            throw new RuntimeException("Failed to stringify object", e);
        }
    }

    public <T> T objectFromString(String src, Class<T> clazz) {
        try {
            return jsonMapper.readValue(src, clazz);
        } catch (IOException ioExc) {
            log.error("Failed to deserialize from string " + src, ioExc);
            throw new RuntimeException("Failed to create object from String", ioExc);
        }
    }
}

