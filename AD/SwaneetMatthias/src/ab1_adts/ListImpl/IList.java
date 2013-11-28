package ab1_adts.ListImpl;

import ad_utils.ITimeSpace;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public interface IList<T extends Comparable<T>> extends ITimeSpace {

	// add elem to the end in O(1)
	// last element is saved from now on...
	void append(T elem);

	// Check if a given List is Sorted
	boolean isSorted();

	// add elem to the front
	void cons(T elem);

	// get Last Element without removing it
	T last();

	// removes first element and return first
	T head();

	// Not in interface but a simple get(index) method
	// throws outofbound when n > arrlength-1 && n < 0
	T get(int index);

	// get first element
	T first();

	// get first element
	Node<T> getFirst();

	// how many elements has a list?
	int length();

	// is the list empty?
	boolean isempty();

	// insert element after index n so between n and n+2 if there is a current
	// n+2
	void insert(T elem, int n);

}
