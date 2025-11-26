package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonPathExample {
    private static final Logger logger = LoggerFactory.getLogger(JsonPathExample.class);
    
    public static void runExample() {
        // Turn off DEBUG logging for JsonPath internal classes
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.jayway.jsonpath"))
            .setLevel(ch.qos.logback.classic.Level.WARN);
   
        try {
            // Load JSON from resources
            InputStream inputStream = JsonPathExample.class.getClassLoader().getResourceAsStream("immunization-resource.json");
            if (inputStream == null) {
                logger.error("Could not find immunization-resource.json");
                return;
            }
            
            // Read the JSON content as string
            String jsonContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            
            // Create custom configuration with options
            System.out.println("\n=== Configuration.builder Example ===");
            Configuration config = Configuration.builder()
                .options(Option.DEFAULT_PATH_LEAF_TO_NULL)  // Return null for missing paths instead of throwing exception
                .options(Option.SUPPRESS_EXCEPTIONS)       // Suppress PathNotFoundException
                .options(Option.REQUIRE_PROPERTIES)        // Require properties to exist (turns off internal logging)
                .build();
            
            // Create DocumentContext with custom configuration
            DocumentContext document = JsonPath.using(config).parse(jsonContent);

            // Get the full objects
            final List<Object> sites = new ArrayList<>(document.read("$.entry[*].resource"));
            final List<Object> subsites = new ArrayList<>(document.read("$.entry[*].resource.sites[*]"));
            sites.addAll(subsites);

            // Print all extracted values by extracting names from objects
            for (Object site : sites) {
                if (site instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> siteMap = (Map<String, Object>) site;
                    String name = (String) siteMap.get("name");
                    System.out.println("Extracted Site/Subsite: " + name);
                }
            }
            
        } catch (IOException e) {
            logger.error("Error reading JSON file", e);
        } catch (Exception e) {
            logger.error("Error parsing JSON with JsonPath", e);
        }
    }
}