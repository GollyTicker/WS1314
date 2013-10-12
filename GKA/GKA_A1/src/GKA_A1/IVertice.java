package GKA_A1;

import java.util.Set;

public interface IVertice {

	// Selectors
	String getName();

	int getValV(String attr);

	String getStrV(String attr);

	Set<String> getAttrV();

	// Mutators
	void setValV(String attr, int val);

	void setStrV(String attr, String val);
	
	boolean hasVertice(long vID);
	
	boolean addVertice(long vID);
	
}
