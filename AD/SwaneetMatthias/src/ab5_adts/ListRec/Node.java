package ab5_adts.ListRec;

/**
 *
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public class Node<T> implements INode<T> {

	private T elem;
	private Node<T> nextNode = null;
	
	public Node(T elem) {
		this.elem = elem;
	}

	@Override
	public void next(Node<T> node) {
		this.nextNode = node;
	}

	public void setElem(T t) {
		this.elem = t;
	}

	@Override
	public T getElem() {
		return this.elem;
	}

	@Override
	public Node<T> getNext() {
		return this.nextNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elem == null) ? 0 : elem.hashCode());
		result = prime * result
				+ ((nextNode == null) ? 0 : nextNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Node<?>))
			return false;
		Node<?> other = (Node<?>) obj;
		return this.getElem() == other.getElem()
				&& this.getNext() == other.getNext();
	}

	@Override
	public String toString() {
		return "" + getElem();
	}

}
