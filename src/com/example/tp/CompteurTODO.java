package com.example.tp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompteurTODO {
    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get("."))) {
            Map<Path, Long> todoParFichier = paths
                .filter(p -> p.toString().endsWith(".java"))
                .collect(Collectors.toMap(
                    p -> p,
                    p -> {
                        try (Stream<String> lines = Files.lines(p)) {
                            return lines.filter(line -> line.contains("TODO")).count();
                        } catch (IOException e) {
                            return 0L;
                        }
                    }
                ));
            
            System.out.println("Nombre de TODO par fichier Java:");
            todoParFichier.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .sorted(Map.Entry.<Path, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey().getFileName() + ": " + e.getValue()));
            
            long totalTODO = todoParFichier.values().stream().mapToLong(Long::longValue).sum();
            System.out.println("Total des TODO: " + totalTODO);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}