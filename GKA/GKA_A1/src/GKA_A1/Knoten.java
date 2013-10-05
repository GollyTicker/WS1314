/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GKA_A1;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Swaneet
 */
public class Knoten {
    
    // Names - Values
    private Map<String,String> attrs = new HashMap<>();
    private String name;
    private static long IDcounter = 0;
    public final long ID;
    
    Knoten(String name){
        ID = IDcounter;
        IDcounter+=1;
        this.name = name;
    }
    
    // SELEKTOREN
    
    public String getName(){
        return this.name;
    }
    
    
    int getValV(String attr){
        if(attrs.containsKey(attr)){
            return Integer.parseInt(attrs.get(attr));
        }
        return Integer.MAX_VALUE;
    }
    
    String getStrV(String attr){
        if(attrs.containsKey(attr)){
            return attrs.get(attr);
        }
        return "";
    }
    
    List<String> getAttrV(){
        return new ArrayList<>(attrs.keySet());
    }
    
    
    
    // MUTATOREN
    
    void setValV(String attr, int val){
        attrs.put(attr, Integer.valueOf(val).toString());
    }
    
    void setStrV(String attr, String val){
        attrs.put(attr, val);
    }
    
    @Override public String toString(){
        return this.getName() + ": " + this.ID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (int) (this.ID ^ (this.ID >>> 32));
        return hash;
    }
    
    @Override public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Knoten)) return false;
        Knoten k = (Knoten)o;
        return this.ID == k.ID;
    }
    
}
