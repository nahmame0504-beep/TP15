package com.example.tp;

import java.util.function.IntUnaryOperator;

public class CaptureLambda {
    public static void main(String[] args) {
        // Capture d'une variable locale (effectively final)
        int facteur = 10;
        IntUnaryOperator multiplicateur = n -> n * facteur;
        
        System.out.println("5 * " + facteur + " = " + multiplicateur.applyAsInt(5));
        
        // Capture de this
        new CaptureLambda().demoThis();
    }
    
    private int valeur = 100;
    
    private void demoThis() {
        Runnable r = () -> {
            System.out.println("Valeur capturée via this: " + this.valeur);
        };
        r.run();
    }
}