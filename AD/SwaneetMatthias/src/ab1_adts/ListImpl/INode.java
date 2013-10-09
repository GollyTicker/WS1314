package ab1_adts.ListImpl;

public interface INode<T> {
	
	void next(Node<T> node);
	
	T getElem();
	
	Node<T> getNext();

}
