package com.example;

import java.util.*;
import java.util.function.*;
import java.time.*;
import reactor.test.StepVerifier;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//import static org.assertj.core.api.Assertions.*;

public class App {
    public static void main(String[] args) {
        
        System.out.println("=== Merging Two Fluxes ===");
        
        // Create first flux with 5 letters (a-e) with different delays
        Flux<String> flux1 = Flux.just("a", "b", "c", "d", "e")
            .delayElements(Duration.ofMillis(200))
            .doOnNext(letter -> System.out.println("Flux1 emitted: " + letter));
        
        // Create second flux with 5 letters (f-j) with different delays
        Flux<String> flux2 = Flux.just("f", "g", "h", "i", "j")
            .delayElements(Duration.ofMillis(150))
            .doOnNext(letter -> System.out.println("Flux2 emitted: " + letter));
        
        System.out.println("Starting merge operation...");
        
        // Method 1: Using Flux.merge() - subscribes to both fluxes simultaneously
        // Results will interleave based on timing
        System.out.println("\n--- Using Flux.merge() ---");
        Flux<String> merged = Flux.merge(flux1, flux2);
        
        merged
            .doOnNext(result -> System.out.println("Merged result: " + result))
            .blockLast(); // Block to wait for completion
        
        System.out.println("\n--- Using flux1.mergeWith(flux2) ---");
        // Alternative syntax - same behavior as Flux.merge()
        flux1 = Flux.just("a", "b", "c", "d", "e")
            .delayElements(Duration.ofMillis(200));
        flux2 = Flux.just("f", "g", "h", "i", "j")
            .delayElements(Duration.ofMillis(150));
        
        flux1.mergeWith(flux2)
            .doOnNext(result -> System.out.println("MergeWith result: " + result))
            .blockLast();
        
        System.out.println("\n--- Using Flux.concat() for ordered merging ---");
        // For comparison: concat waits for first flux to complete before starting second
        flux1 = Flux.just("a", "b", "c", "d", "e")
            .delayElements(Duration.ofMillis(100));
        flux2 = Flux.just("f", "g", "h", "i", "j")
            .delayElements(Duration.ofMillis(100));
        
        Flux.concat(flux1, flux2)
            .doOnNext(result -> System.out.println("Concat result: " + result))
            .blockLast();
        
        System.out.println("All done!");
    }

}

