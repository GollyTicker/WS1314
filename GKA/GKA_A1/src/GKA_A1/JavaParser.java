/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GKA_A1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;

import GKA_A1.GKA_IMPL.AIGraph;
import GKA_A1.GKA_IMPL.AIGraphImpl;

/**
 *
 * @author Swaneet
 */
public class JavaParser {

    private String src = "";

    public JavaParser(String src) {
        this.src = src;
    }

    public AIGraph createGraph() throws FileNotFoundException, java.io.IOException {
        AIGraph graph = new AIGraphImpl();
        BufferedReader br = new BufferedReader(new FileReader(this.src));

        // TODO:
        //boolean isdirected;
        try {
            StringBuilder sb = new StringBuilder();
            String line = null;
            line = UTF8EncodedString(br.readLine());
            int count = 0;
            while (line != null && line != "") {
                if (count > 0) {
                    String[] s = line.split(",");
                    if (s.length <= 2) {
                        break;
                    }
                    //puts(s);
                    long v1ID = graph.addVertex(s[0]);
                    long v2ID = graph.addVertex(s[1]);

                    long eID = graph.addEdgeU(v1ID, v2ID);
                    graph.setValE(eID, "km", Integer.parseInt(s[2]));
                }
                count += 1;
                line = UTF8EncodedString(br.readLine());
            }

        } finally {
            br.close();
        }
        return graph;
    }

    private String UTF8EncodedString(String line) {
        if (line != null) {
            return new String(line.getBytes(), Charset.forName("UTF-8"));
        }
        return "";
    }

    void puts(Object o) {
        System.out.println(o.toString());
    }

    void puts(String[] s) {
        String stracc = "";
        for (String s_ : s) {
            stracc += s_ + ",";
        }
        System.out.println("Splited: " + stracc);
    }
}
