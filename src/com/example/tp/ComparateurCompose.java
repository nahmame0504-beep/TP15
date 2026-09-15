package com.example.tp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PersonneComp {
    private String nom;
    private String prenom;
    private int age;
    
    public PersonneComp(String prenom, String nom, int age) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }
    
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return prenom + " " + nom + " (" + age + ")";
    }
}

public class ComparateurCompose {
    public static void main(String[] args) {
        List<PersonneComp> personnes = Arrays.asList(
            new PersonneComp("Jean", "Dupont", 30),
            new PersonneComp("Marie", "Martin", 25),
            new PersonneComp("Pierre", "Dupont", 40),
            new PersonneComp("Sophie", "Martin", 35),
            new PersonneComp("Paul", "Dupont", 20)
        );
        
        Comparator<PersonneComp> comparateur = Comparator
            .comparing(PersonneComp::getNom)
            .thenComparing(PersonneComp::getPrenom)
            .thenComparingInt(PersonneComp::getAge);
        
        System.out.println("Liste triée:");
        personnes.stream()
            .sorted(comparateur)
            .forEach(System.out::println);
        
        Comparator<PersonneComp> comparateurVariante = Comparator
            .comparing(PersonneComp::getNom)
            .thenComparing(PersonneComp::getAge, Comparator.reverseOrder());
        
        System.out.println("\nListe triée (variante):");
        personnes.stream()
            .sorted(comparateurVariante)
            .forEach(System.out::println);
    }
}