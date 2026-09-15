package com.example.tp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
interface IOFunction<T, R> {
    R apply(T t) throws IOException;
    
    static <T, R> Function<T, R> unchecked(IOFunction<T, R> f) {
        return t -> {
            try {
                return f.apply(t);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}

public class ExceptionsLambda {
    public static void main(String[] args) {
        // Exemple 1: Gestion directe
        try (Stream<Path> paths = Files.list(Paths.get("."))) {
            List<String> fichiers = paths
                    .map(p -> {
                        try {
                            return Files.size(p) + " - " + p.getFileName();
                        } catch (IOException e) {
                            return "Erreur: " + p.getFileName();
                        }
                    })
                    .collect(Collectors.toList());
            System.out.println("Fichiers: " + fichiers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Exemple 2: Interface wrapper IOFunction
        try (Stream<Path> paths = Files.list(Paths.get("."))) {
            List<Long> tailles = paths
                    .map(IOFunction.unchecked(Files::size))
                    .collect(Collectors.toList());
            System.out.println("Tailles: " + tailles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}