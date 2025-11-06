package com.example;

import java.util.*;
import java.util.function.*;
import java.time.*;
import reactor.test.StepVerifier;

import reactor.core.publisher.Flux;

//import static org.assertj.core.api.Assertions.*;

public class App {
    public static void main(String[] args) {

        // Create Flux from static User objects using Flux.just()
        Flux<User> userFlux = Flux.just(
            User.SKYLER, 
            User.JESSE, 
            User.WALTER, 
            User.SAUL
        );

        System.out.println("=== Users from Flux ===");
        userFlux.subscribe(user -> 
            System.out.println("User: " + user.getFirstname() + " " + user.getLastname())
        );

        
        System.out.println("=== Verifying the First User in our Flux ===");
        try {
            StepVerifier.create(userFlux)
                .expectNextMatches(user -> "pinkman".equals(user.getUsername()))
                .expectNextCount(3)
                .expectComplete()
                .verify();
            System.out.println("Test passed!");
        } catch (AssertionError e) {
            System.err.println("Test failed: " + e.getMessage());
            System.out.println("This is expected since 'pinkman' is not the first user.");
        }

        // Let's also show a test that will pass
        System.out.println("\n=== Correct test - checking for 'swhite' ===");
        try {
            StepVerifier.create(userFlux)
                .expectNextMatches(user -> "swhite".equals(user.getUsername()))
                .thenCancel()
                .verify();
            System.out.println("Test passed - first user is indeed 'swhite'!");
        } catch (AssertionError e) {
            System.err.println("Unexpected failure: " + e.getMessage());
        }

        System.out.println("All done!");
    }

}

