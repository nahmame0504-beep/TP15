package com.example.tp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class PersonneStream {
    private String nom;
    private int age;
    private String ville;
    
    public PersonneStream(String nom, int age, String ville) {
        this.nom = nom;
        this.age = age;
        this.ville = ville;
    }
    
    public String getNom() { return nom; }
    public int getAge() { return age; }
    public String getVille() { return ville; }
    
    @Override
    public String toString() {
        return nom + " (" + age + ") - " + ville;
    }
}

public class StreamAvance {
    public static void main(String[] args) {
        List<PersonneStream> personnes = Arrays.asList(
            new PersonneStream("Jean", 25, "Paris"),
            new PersonneStream("Marie", 30, "Lyon"),
            new PersonneStream("Pierre", 20, "Paris"),
            new PersonneStream("Sophie", 35, "Lyon"),
            new PersonneStream("Paul", 40, "Marseille")
        );
        
        // Grouper par ville
        Map<String, List<PersonneStream>> parVille = personnes.stream()
                .collect(Collectors.groupingBy(PersonneStream::getVille));
        System.out.println("Personnes par ville: " + parVille);
        
        // Âge moyen
        double ageMoyen = personnes.stream()
                .mapToInt(PersonneStream::getAge)
                .average()
                .orElse(0);
        System.out.println("Âge moyen: " + ageMoyen);
        
        // Personne la plus âgée
        PersonneStream plusAgee = personnes.stream()
                .max(Comparator.comparingInt(PersonneStream::getAge))
                .orElse(null);
        System.out.println("Personne la plus âgée: " + plusAgee);
        
        // Noms des personnes de Paris en majuscules
        List<String> parisiens = personnes.stream()
                .filter(p -> "Paris".equals(p.getVille()))
                .map(PersonneStream::getNom)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Parisiens: " + parisiens);
    }
}