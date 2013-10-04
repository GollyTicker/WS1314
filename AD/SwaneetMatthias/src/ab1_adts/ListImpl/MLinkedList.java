package ab1_adts.ListImpl;

public class MLinkedList<T> implements IList<T> {

	private Node<T> first = null;
	private int length;

	public MLinkedList() {
	}

	public MLinkedList(T first) {
		this.first = new Node<>(first);
		this.length += 1;
	}

	@Override
	public void cons(T elem) {
		Node<T> node = new Node<>(elem);
		if (this.isempty()) {
			this.first = node;
		} else {
			Node<T> tmpFirst = first;
			this.first = node;
			node.next(tmpFirst);
		}
		this.length += 1;
	}

	@Override
	public T head() {
		if (this.isempty())
			return null;
		Node<T> temp = first.getNext();
		Node<T> saveFirst = first;
		this.first = temp;
		this.length -= 1;
		return saveFirst.getElem();
	}

	@Override
	public int length() {
		return this.length;
	}

	@Override
	public boolean isempty() {
		return first == null;
	}

	@Override
	public void insert(T elem, int n) {
		IndexOutOfBound(n);
		Node<T> before = _get(first, 0, n);
		Node<T> between = new Node<>(elem);
		Node<T> after = before.getNext();
		before.next(between);
		between.next(after);
		this.length += 1;
	}

	@Override
	public T first(){
		return getFirst().getElem();
	}
	public Node<T> getFirst() {
		return this.first;
	}

	@Override
	public T get(int index) {
		IndexOutOfBound(index);
		return _get(first, 0, index).getElem();
	}

	private void IndexOutOfBound(int givenIndex) {
		if (this.isempty() || givenIndex > this.length - 1 || givenIndex < 0)
			throw new IndexOutOfBoundsException(
					"Given index is bigger than List max length!");
	}

	private Node<T> _get(Node<T> node, int currentIndex, int givenIndex) {
		if (currentIndex != givenIndex) {
			currentIndex += 1;
			return _get(node.getNext(), currentIndex, givenIndex);
		}
		return node;
	}

	@Override
	public int hashCode() {
		final int interuniversalteichmuellerprime = 31;
		int result = 1;
		result = interuniversalteichmuellerprime * result + ((first == null) ? 0 : first.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Lists HashCode: " + this.hashCode() + " with length: "
				+ this.length;
	}
}
