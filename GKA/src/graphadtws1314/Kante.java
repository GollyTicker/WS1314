/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphadtws1314;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Swaneet
 */
public class Kante {
    
    private Map<String,String> attrs = new HashMap<>();
    private static long IDcounter = 0;
    public final long ID;
    private boolean isdirected;
    private long srcVID;
    private long destVID;
    
    Kante(boolean isdirected, long srcVID, long destVID){
        ID = IDcounter;
        IDcounter += 1;
        this.isdirected = isdirected;
        this.srcVID = srcVID;
        this.destVID = destVID;
    }
    
    long SourceVertex(){
        return srcVID;
    }
    
    long DestVertex(){
        return destVID;
    }
    
    boolean isDirected(){
        return isdirected;
    }
    
    // SELEKTOREN
    
    
    int getValE(String attr){
        if(attrs.containsKey(attr)){
            return Integer.parseInt(attrs.get(attr));
        }
        return Integer.MAX_VALUE;
    }
    
    String getStrE(String attr){
        if(attrs.containsKey(attr)){
            return attrs.get(attr);
        }
        return "";
    }
    
    List<String> getAttrE(){
        return new ArrayList<>(attrs.keySet());
    }
    
    
    
    // MUTATOREN
    
    void setValE(String attr, int val){
        attrs.put(attr, Integer.valueOf(val).toString());
    }
    
    void setStrE(String attr, String val){
        attrs.put(attr, val);
    }
    
    @Override public String toString(){
        return "Edge: " + this.ID + "; between: " + this.srcVID + " and " + this.destVID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.ID ^ (this.ID >>> 32));
        return hash;
    }
    
    @Override public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Kante)) return false;
        Kante k = (Kante)o;
        return this.ID == k.ID;
    }
    
    
}
