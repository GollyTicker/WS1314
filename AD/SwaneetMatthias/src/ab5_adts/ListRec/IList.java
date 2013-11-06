package ab5_adts.ListRec;

import ad_utils.ITimeSpace;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */

public interface IList<T> extends ITimeSpace {

	// add elem to the front
	void cons(T elem);

	// removes first element and return first
	T head();

	// Not in interface but a simple get(index) method
	// throws outofbound when n > arrlength-1 && n < 0
	T get(int index);

	// get first element
	T first();

	// get last element
	T last();

	// get all but first element
	IList<T> tail();

	// get all except last element
	IList<T> init();

	// how many elements has a list?
	int length();

	// is the list empty?
	boolean isempty();

	void insert(T elem, int n);

	void insertIter(T elem, int n);

}
