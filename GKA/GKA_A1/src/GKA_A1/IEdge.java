package GKA_A1;

import java.util.Set;

public interface IEdge {

	long getSrcVId();

	long getDestVId();

	Set<Long> getSourceTarget();

	int getValE(String attr);

	String getStrE(String attr);

	Set<String> getAttrE();

	void setValE(String attr, int val);

	void setStrE(String attr, String val);

	boolean hasVertice(long vId);

	boolean isDirected();
}
