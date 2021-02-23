package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexConstraint {
    public static void main( String[] args ) {
        //CSP
        //Variables
        List<String> variables = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA");

        //Dominios
        Map<String, List<String>> domains = new HashMap();
        for (var variable : variables) {
            domains.put(variable, List.of("Cyan", "Magenta", "Yellow", "Key"));
        }

        CSP<String, String> problema = new CSP(variables, domains);
        //Restricciones
        problema.addConstraint(new ComplexColoringConstraint("A", "B"));
        problema.addConstraint(new ComplexColoringConstraint("A", "V"));
        problema.addConstraint(new ComplexColoringConstraint("B", "V"));
        problema.addConstraint(new ComplexColoringConstraint("C", "D"));
        problema.addConstraint(new ComplexColoringConstraint("C", "F"));
        problema.addConstraint(new ComplexColoringConstraint("C", "H"));
        problema.addConstraint(new ComplexColoringConstraint("D", "G"));
        problema.addConstraint(new ComplexColoringConstraint("D", "H"));
        problema.addConstraint(new ComplexColoringConstraint("E", "I"));
        problema.addConstraint(new ComplexColoringConstraint("F", "K"));
        problema.addConstraint(new ComplexColoringConstraint("G", "K"));
        problema.addConstraint(new ComplexColoringConstraint("H", "M"));
        problema.addConstraint(new ComplexColoringConstraint("H", "V"));
        problema.addConstraint(new ComplexColoringConstraint("I", "M"));
        problema.addConstraint(new ComplexColoringConstraint("J", "N"));
        problema.addConstraint(new ComplexColoringConstraint("K", "J"));
        problema.addConstraint(new ComplexColoringConstraint("K", "O"));
        problema.addConstraint(new ComplexColoringConstraint("L", "P"));
        problema.addConstraint(new ComplexColoringConstraint("L", "Q"));
        problema.addConstraint(new ComplexColoringConstraint("M", "Q"));
        problema.addConstraint(new ComplexColoringConstraint("P", "S"));
        problema.addConstraint(new ComplexColoringConstraint("P", "T"));
        problema.addConstraint(new ComplexColoringConstraint("P", "U"));
        problema.addConstraint(new ComplexColoringConstraint("R", "S"));
        problema.addConstraint(new ComplexColoringConstraint("R", "W"));
        problema.addConstraint(new ComplexColoringConstraint("R", "X"));
        problema.addConstraint(new ComplexColoringConstraint("S", "X"));
        problema.addConstraint(new ComplexColoringConstraint("S", "Y"));
        problema.addConstraint(new ComplexColoringConstraint("S", "Z"));
        problema.addConstraint(new ComplexColoringConstraint("T", "Z"));
        problema.addConstraint(new ComplexColoringConstraint("T", "AA"));
        problema.addConstraint(new ComplexColoringConstraint("T", "U"));
        problema.addConstraint(new ComplexColoringConstraint("U", "AA"));
        problema.addConstraint(new ComplexColoringConstraint("V", "W"));
        problema.addConstraint(new ComplexColoringConstraint("W", "X"));
        problema.addConstraint(new ComplexColoringConstraint("X", "Y"));
        problema.addConstraint(new ComplexColoringConstraint("Y", "Z"));
        problema.addConstraint(new ComplexColoringConstraint("Z", "AA"));
        var solution = problema.backtrackAC3();
        System.out.println(solution);
    }
}
