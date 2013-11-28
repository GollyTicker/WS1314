package ab1_adts.ListImpl;

public class MListIterator<T extends Comparable<T>> {

	private INode<T> node;

	public MListIterator(IList<T> l) {
		node = new Node<T>(null);
		node.next(l.getFirst());
	}

	public boolean hasNext() {
		return node.getNext() != null;
	}

	public INode<T> next() {
		node = node.getNext();
		return node;
	}

}
