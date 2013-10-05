/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GKA_A1;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GraphADTws1314 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        JavaParser jp = new JavaParser("C:\\Users\\Swaneet\\Desktop\\HAW\\graph_01.graph");
        AIGraph yolo = jp.createGraph();
        
        System.out.println(yolo.toString());
    }
}
