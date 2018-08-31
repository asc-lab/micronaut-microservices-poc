package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class JsonConverter {
    private final ObjectMapper jsonMapper;
    
    public  <T> String stringifyObject(T doc){
        try {
            return jsonMapper.writeValueAsString(doc);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialized policy to json", e);
            return null;
        }
    }
    
    public <T> T objectFromString(String src, Class<T> classz){
        try {
            return jsonMapper.readValue(src, classz);
        } catch (IOException ioExc) {
            log.error("Failed to deserialize from string " + src, ioExc);
            return null;
        }
    }
}
