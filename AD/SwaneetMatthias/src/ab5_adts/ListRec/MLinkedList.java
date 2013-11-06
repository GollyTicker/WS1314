package ab5_adts.ListRec;

import ab5_adts.ListRec.*;
import ad_utils.ITimeSpace;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public class MLinkedList<T> implements IList<T>, ITimeSpace {

	private Node<T> first = null;
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
	 * element already included.
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
		CheckListNotEmpty();
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
	 * Insert the element at the given index. The old element at the given index
	 * gets pushed to the back
	 * 
	 * @param elem
	 *            the element to be inserted
	 * @param n
	 *            the index where it's to be included
	 */
	@Override
	public void insertIter(T elem, int n) {
		IndexOutOfBound(n);
		if (n == 0) {
			cons(elem);
			return;
		}
		accessCount += 1; // first
		Node<T> before = _get(first, 0, n - 1);
		Node<T> between = new Node<>(elem);
		accessCount += 1; // getNext()
		Node<T> after = before.getNext();
		before.next(between);
		between.next(after);
		this.length += 1;
	}

	/**
	 * Insert the element at the
	 * 
	 * @param elem
	 *            the element to be inserted
	 * @param n
	 *            the index where it's to be included
	 */
	@Override
	public void insert(T elem, int n) {
		IndexOutOfBound(n);
		insertHelper(elem, n);
	}

	private void insertHelper(T elem, int n) {
		// ins(y, 0, xs) = y:xs
		if (n == 0) {
			cons(elem);
		} else {
			// ins(y, n , hd:tl) = hd:ins(y, n-1, tl)

			// we need to save a reference to the currently first
			// element, because the imperative nature of this insert(...)
			// head <= hd
			T head = this.first();

			// the current list(this) becomes tail
			// this <= tl
			this.tail();
			
			// insert recursively
			// this <= ins(y, n-1, tl)
			this.insert(elem, n - 1);
			
			// this <= hd:ins(y, n-1, tl)
			this.cons(head);
			
			// now we have this == ins(y, n , hd:tl) == hd:ins(y, n-1, tl)
		}
	}

	/**
	 * Returns the first element of the list
	 */
	@Override
	public T first() {
		CheckListNotEmpty();
		return getFirst().getElem();
	}

	public Node<T> getFirst() {
		CheckListNotEmpty();
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

	private void CheckListNotEmpty() {
		if (this.isempty())
			throw new IndexOutOfBoundsException(
					"Cannot use this Operation on an empty list. Here, have a Cookie....");
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
	public int hashCode() {
		final int inter_universal_teichmueller_prime = 31;
		int result = 1;
		result = inter_universal_teichmueller_prime * result
				+ ((first == null) ? 0 : first.hashCode());
		accessCount += 1; // .first
		return result;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (!(o instanceof IList<?>))
			return false;

		IList<T> otherList = (IList<T>) o;
		if (this.length() != otherList.length()) {
			return false;
		}

		for (int i = 0; i < this.length(); i++)
			if (!this.get(i).equals(otherList.get(i)))
				return false;

		return true;

	}

	@Override
	public String toString() {
		accessCount += 1; // .first()
		return stringHelper(this.first, "List[") + "]";
	}

	private String stringHelper(Node<T> elem, String accu) {
		accessCount += 1; // getNext()
		Node<T> next = elem.getNext();
		if (next == null)
			return accu + elem;
		return stringHelper(next, accu + elem.getElem() + ", ");
	}

	@Override
	public void tail() {
		CheckListNotEmpty();
		accessCount += 1; // .getNext()
		this.first = first.getNext();
		this.length -= 1;
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

}
