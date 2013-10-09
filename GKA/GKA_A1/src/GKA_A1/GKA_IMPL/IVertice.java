package GKA_A1.GKA_IMPL;

import java.util.List;

public interface IVertice {

	// Selectors
	String getName();

	int getValV(String attr);

	String getStrV(String attr);

	List<String> getAttrV();

	// Mutators
	void setValV(String attr, int val);

	void setStrV(String attr, String val);
	
	boolean hasVertice(long vID);
	
	boolean addVertice(long vID);
	
}
