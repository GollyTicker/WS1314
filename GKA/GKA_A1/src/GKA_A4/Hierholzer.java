package GKA_A4;

import GKA_A1.IAIGraph;

public class Hierholzer {

	private IAIGraph graph;
	private String cmp;

	public Hierholzer(IAIGraph graph, String cmp) {
		this.graph = graph;
		this.cmp = cmp;
	}
	
	
	public void hierholzeEs(){
		
	}
//	Voraussetzung: Sei G=(V,E) ein zusammenhängender Graph, der nur Knoten mit geradem Grad aufweist.
//		1. Wähle einen beliebigen Knoten v_0 des Graphen und konstruiere von v_0 ausgehend einen 
//		Unterkreis K in G, der keine Kante in G zweimal durchläuft.
//		2. Wenn K ein Eulerkreis ist, breche ab. Andernfalls:
//		3. Vernachlässige nun alle Kanten des Unterkreises K.
//		4. Am ersten Eckpunkt von K, dessen Grad größer 0 ist, lässt man nun einen weiteren Unterkreis K' 
//		entstehen, der keine Kante in K durchläuft und keine Kante in G zweimal enthält.
//		5. Füge in K den zweiten Kreis K' ein, indem der Startpunkt von K' durch alle Punkte von K' 
//		in der richtigen Reihenfolge ersetzt wird.
//		6. Nenne jetzt den so erhaltenen Kreis K und fahre bei Schritt 2 fort.

}
