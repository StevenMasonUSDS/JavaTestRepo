package com.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class App {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(JsonPathExample.class);
        
        logger.info("Starting Application");

        // Lambda function examples
        System.out.println("\n=== Java Lambda Function Examples ===");
        
        // Example 1: Simple lambda with functional interface
        Function<String, String> toUpperCase = str -> str.toUpperCase();
        System.out.println("Uppercase: " + toUpperCase.apply("hello world"));
        
        // Example 2: Lambda with predicate
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));
        
        // Example 3: Lambda with streams
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        List<String> filteredNames = names.stream()
            .filter(name -> name.length() > 3)  // Lambda predicate
            .map(name -> name.toUpperCase())    // Lambda function
            .collect(Collectors.toList());
        
        System.out.println("Filtered names: " + filteredNames);
        
        // Example 4: Multi-line lambda
        Function<Integer, String> numberDescription = num -> {
            if (num < 0) {
                return "Negative number: " + num;
            } else if (num == 0) {
                return "Zero";
            } else {
                return "Positive number: " + num;
            }
        };
        
        System.out.println(numberDescription.apply(-5));
        System.out.println(numberDescription.apply(0));
        System.out.println(numberDescription.apply(10));

        // Run the JsonPath configuration example
        //JsonPathExample.runExample();
    }
}

