package ab1_adts.ListImpl;

public interface IList<T> {

	void cons(T elem);

	T head();

	T get(int index);

	T first();

	int length();

	boolean isempty();

	void insert(T elem, int n);

}
