package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSP <V,D>{

    private List<V> variables;
    private Map<V,List<D>> domains;
    private Map<V,List<Constraint<V,D>>> constraints = new HashMap<>();

    public CSP (List<V> variables, Map<V,List<D>> domains){
        this.variables = variables;
        this.domains = domains;

        for (V variable : variables) {
            constraints.put(variable,new ArrayList<Constraint<V,D>>());
            //Cada variable debe teener un dominio asignado
            if(!domains.containsKey(variable)){
                throw new IllegalArgumentException("La variable " + variable + " no contiene un dominio");
            }
        }
    }

    public void addConstraint(Constraint<V,D> constraint){
        for (V variable:constraint.variables) {
            //Variable que se encuentra en el constraint sea parte del csp
            if(!this.variables.contains(variable)) {
                throw new IllegalArgumentException("La variable " + " no se encuentra en el CSP");
            }
            constraints.get(variable).add(constraint);
        }
    }
    public boolean consistent(V variable, Map<V,D> assignment){
        for (Constraint<V,D> constraint:this.constraints.get(variable)) {
            if(!constraint.satisfied(assignment)){
                return false;
            }

        }
        return true;
    }
    public Map<V,D> backtrack(){
        return backtrack(new HashMap<>());
    }
    public Map<V,D> backtrack(Map<V,D> assignment)
    {
        //Si la asignacion es completa (si cada varianle tiene un valor)
        if (variables.size() == assignment.size()){
            return assignment;
        }
        //Seleccionar variable no asignada
        V unassigned = variables.stream()
                .filter(v -> !assignment.containsKey(v))
                .findFirst().get();

        for (D value:domains.get(unassigned)) {
            System.out.println("Variable: " + unassigned + " valor: " + value);
            //Probar asignacion
            //1- Crear copia de la asignacion anterior

            Map<V,D> localAssigment = new HashMap<>(assignment);
            //2- Probar (asignar un valor)

            localAssigment.put(unassigned,value);
            //3- Verificar la consistencia de la asignacion

            if(consistent(unassigned,localAssigment)){
                Map<V,D> result = backtrack(localAssigment);
                if (result != null){
                    return result;
                }
            }
        }
        return null;
    }
    public Map<V, D> backtrackAC3(){
        return backtrackAC3(new HashMap<>(), new HashMap<>(domains));
    }

    public Map<V, D> backtrackAC3(Map<V, D> assignment, Map<V, List<D>> domainsDict){
        //Si la asignacion es completa (si cada varianle tiene un valor)
        if (variables.size() == assignment.size()){
            return assignment;
        }
        //Seleccionar variable no asignada
        V unassigned = variables.stream()
                .filter(v -> !assignment.containsKey(v))
                .findFirst().get();

        for (D value:domains.get(unassigned)) {
            System.out.println("Variable: " + unassigned + " valor: " + value);
            //Probar asignacion
            //1- Crear copia de la asignacion anterior

            Map<V,D> localAssigment = new HashMap<>(assignment);
            //2- Probar (asignar un valor)

            localAssigment.put(unassigned,value);
            //3- Verificar la consistencia de la asignacion

            if(consistent(unassigned,localAssigment)){
                //Con AC3 se realiza el filtrado de los dominios
                Map<V, List<D>> tempD = new HashMap<>(domainsDict);
                for (Constraint<V,D> variable: this.constraints.get(unassigned)) {
                    for (V constraintV: variable.variables) {
                        if(unassigned.equals(constraintV)){
                            tempD.replace(constraintV,domainsDict.get(constraintV)
                                    .stream().filter(v -> v.equals(value)).collect(Collectors.toList()));
                        }
                        else
                        {
                            tempD.replace(constraintV,domainsDict.get(constraintV)
                                    .stream().filter(v -> !v.equals(value)).collect(Collectors.toList()));
                        }
                    }

                }

                Map<V, List<D>> localdomainsDict = tempD;
                Map<V, D> result = backtrackAC3(localAssigment, localdomainsDict);
                if (result != null){
                    return result;
                }
            }
        }
        return null;
    }


}
