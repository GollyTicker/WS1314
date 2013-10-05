package ab1_adts.ListImpl;

public interface IList<T> {

	// add elem to the front
	void cons(T elem);

	// removes first element and return first
	T head();

	// Not in interface but a simple get(index) method
	// throws outofbound when n > arrlength-1 && n < 0
	T get(int index);

	// get first element
	T first();

	// how many elements has a list?
	int length();

	// is the list empty?
	boolean isempty();

	// insert element after index n so between n and n+2 if there is a current
	// n+2
	void insert(T elem, int n);

}
