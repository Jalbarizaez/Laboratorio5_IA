package org.example;

import java.util.List;
import java.util.Map;

public class AustraliaColoringConstraint  extends  Constraint<String,String>{

    public String place1,place2;
    public AustraliaColoringConstraint(String place1, String place2){
        super(List.of(place1,place2));
        this.place1 = place1;
        this.place2 = place2;
    }
    @Override
    public boolean satisfied(Map<String, String> assignment) {
        //La variable no se encuentre aun asignada
        if(!assignment.containsKey(place1)|| !assignment.containsKey(place2)){
            return true;
        }
        //Chequear que lugar 1 <> lugar 2
        return !assignment.get(place1).equals(assignment.get(place2));
    }
}
