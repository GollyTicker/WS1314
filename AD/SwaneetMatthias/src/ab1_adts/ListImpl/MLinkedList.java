package ab1_adts.ListImpl;

import ad_utils.ITimeSpace;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public class MLinkedList<T extends Comparable<T>> implements IList<T>,
		ITimeSpace {

	private Node<T> first = null;
	private Node<T> _last = null;
	private int length = 0;

	// counting references
	private int accessCount = 0;

	// Our interpretation of access:
	// As access(es) count:
	// 1: access on this.first (only access, not overwriting!)
	// 2: calling <Node>.getNext() - access on the next Node
	// 1: this.first is an instancebvarible (not a getter methode). therefore it
	// is counted manually
	// 2: getNext() is also counted manually, because its not implemented
	// in this class where the access is counted.

	/**
	 * Standard-Constructor
	 */
	public MLinkedList() {
	}

	/**
	 * Takes the first element of the list and returns a linked list with this
	 * element alreaady included.
	 * 
	 * @param first
	 *            the first element to be included
	 */
	public MLinkedList(T first) {
		this.first = new Node<>(first);
		this.length += 1;
	}

	/**
	 * Adds the given element at the front of the list.
	 * 
	 * @param elem
	 *            the element to be added
	 */
	@Override
	public void cons(T elem) {
		Node<T> node = new Node<>(elem);
		if (this.isempty()) {
			this.first = node;
			this._last = node;
		} else {
			Node<T> tmpFirst = first;
			accessCount += 1; // Access: first
			this.first = node;
			node.next(tmpFirst);
		}
		this.length += 1;
	}

	/**
	 * Removes and returns the first element of the list. The second element now
	 * becomes the first element.
	 * 
	 * @return the first element of which was just removed
	 */
	@Override
	public T head() {
		if (this.isempty())
			return null;
		Node<T> oldFirst = first;
		accessCount += 1; // first
		Node<T> newFirst = oldFirst.getNext();
		accessCount += 1; // <Node>.getNext()
		this.first = newFirst;
		this.length -= 1;
		return oldFirst.getElem();
	}

	/**
	 * Returns the number of element in the list.
	 * 
	 * @return length
	 */
	@Override
	public int length() {
		return this.length;
	}

	/**
	 * Is true if the list has no elements.
	 * 
	 * @return a boolean value
	 */
	@Override
	public boolean isempty() {
		accessCount += 1; // first
		return first == null;
	}

	/**
	 * On a list with elements from 0 to (n+1) the given element will be
	 * inserted between the n-th and (n+1)-th element. The given element is then
	 * accessible at the index n+1.
	 * 
	 * @param elem
	 *            the element to be inserted
	 * @param n
	 *            the index after which the element is to be included
	 */
	@Override
	public void insert(T elem, int n) {
		IndexOutOfBound(n);
		Node<T> before = _get(first, 0, n);
		accessCount += 1; // first
		Node<T> between = new Node<>(elem);
		Node<T> after = before.getNext();
		accessCount += 1; // first
		before.next(between);
		between.next(after);
		if (n == this.length)
			this._last = between;
		this.length += 1;
	}

	/**
	 * Returns the first element of the list
	 */
	@Override
	public T first() {
		return getFirst().getElem();
	}

	@Override
	public Node<T> getFirst() {
		accessCount += 1; // first
		return this.first;
	}

	/**
	 * Returns the element at the given index. If the list is empty or the given
	 * index is higher than the size of the list - 1 or the index is lower than
	 * 0, then an IndexOutOfBoundsException is raised.
	 * 
	 * @param index
	 *            the index of the element to be returned
	 * @return the element of the given index
	 */
	@Override
	public T get(int index) {
		IndexOutOfBound(index);
		accessCount += 1; // first
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

			accessCount += 1; // .getNext()
			return _get(node.getNext(), currentIndex, givenIndex);
		}
		return node;
	}

	@Override
	public boolean isSorted() {
		for (int i = 0; i < length - 1; i++)
			if (get(i).compareTo(get(i + 1)) == 1)
				return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int inter_universal_teichmueller_prime = 31;
		int result = 1;
		result = inter_universal_teichmueller_prime * result
				+ ((first == null) ? 0 : first.hashCode());
		return result;
	}

	@Override
	public String toString() {
		if (first == null)
			return "List[]";
		return stringHelper(this.first, "List[") + "]";
	}

	private String stringHelper(Node<T> elem, String accu) {
		accu += ((elem == first) ? "" : ", ") + elem.getElem();
		if (elem.getNext() == null)
			return accu;
		return stringHelper(elem.getNext(), accu);
	}

	@Override
	public void printCount() {
		System.out.println("AccessCount: " + accessCount);
	}

	@Override
	public int accessCount() {
		return this.accessCount;
	}

	@Override
	public void resetAccessCount() {
		this.accessCount = 0;
	}

	@Override
	public void setAccessCount(int ac) {
		this.accessCount = ac;
	}

	@Override
	public int memoryUsage() {
		return this.length;
	}

	@Override
	public void append(T elem) {
		if(this.isempty()){
			this.cons(elem);
			return ;
		}
		Node<T> elemN = new Node<T>(elem);
		Node<T> tmp = this._last;
		tmp.next(elemN);
		this._last = elemN;
		this.length += 1;
	}

	@Override
	public T last() {
		return this._last.getElem();
	}
}
