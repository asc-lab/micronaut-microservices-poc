package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
@Slf4j
@RequiredArgsConstructor
class JsonConverter {
    private final ObjectMapper jsonMapper;

    <T> String stringifyObject(T doc) {
        try {
            return jsonMapper.writeValueAsString(doc);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialized policy to json", e);
            return null;
        }
    }

    <T> T objectFromString(String src, Class<T> clazz) {
        try {
            return jsonMapper.readValue(src, clazz);
        } catch (IOException ioExc) {
            log.error("Failed to deserialize from string " + src, ioExc);
            return null;
        }
    }
}
