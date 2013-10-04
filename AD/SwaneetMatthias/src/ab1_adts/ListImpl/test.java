package ab1_adts.ListImpl;

public class test {

	public static void main(String[]args){
		MLinkedList<Integer> mll = new MLinkedList<>(2);
		mll.cons(1);
		mll.cons(5);
		mll.cons(1);
		mll.cons(1);
		System.out.println(mll);
		mll.head();
		mll.head();
		System.out.println(mll);
		System.out.println(mll.get(mll.length()-1));
	}
}
