package ab1_adts.ListImpl;

public class MListIterator<T extends Comparable<T>> {

	private INode<T> node;

	public MListIterator(MLinkedList<T> l) {
		node = new Node<T>(null);
		node.next(l.getFirst());
	}

	public boolean hasNext() {
		return node.getNext() != null;
	}

	public T next() {
		node = node.getNext();
		if(node == null)
			return null;
		return node.getElem();
	}

}
