package GKA_A1;

import java.util.ArrayList;
import java.util.List;

public interface IEdge {

	long getSrcVId();

	long getDestVId();
	
	ArrayList<Long> getSourceTarget();

	int getValE(String attr);

	String getStrE(String attr);

	List<String> getAttrE();

	void setValE(String attr, int val);

	void setStrE(String attr, String val);
	
	boolean hasVertice(long vId);
	
	boolean isDirected();
}
