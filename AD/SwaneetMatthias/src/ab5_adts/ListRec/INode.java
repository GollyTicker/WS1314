package ab5_adts.ListRec;

/**
 *
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public interface INode<T> {
	
	void next(Node<T> node);
	
	T getElem();
	
	Node<T> getNext();

}
