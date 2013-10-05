package ab1_adts.ListImpl;

public class MLinkedList<T> implements IList<T> {

	private Node<T> first = null;
	private int length;
        
        
        // counting references
        private int accessCount = 0;
        public void printCount(){
            System.out.println("AccessCount: " + accessCount);
        }        
        
        // Our interpretation of access:
        // As access(es) count:
        //		1: access on this.first (only access, not overwriting!)
        // 		2:  calling <Node>.getNext() - access on the next Node
        // 1: this.first is an instancebvarible (not a getter methode). therefore it is counted manually
        // 2: getNext() is also counted manually, because its not implemented
        // 		in this class where the access is counted.

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
			Node<T> tmpFirst = first;   accessCount+=1; // Access: first
			this.first = node;
			node.next(tmpFirst);
		}
		this.length += 1;
	}

	@Override
	public T head() {
		if (this.isempty())
			return null;
		Node<T> temp = first.getNext();     accessCount+=2; // 1: first; 2: <Node>.getNext()
		Node<T> saveFirst = first;          accessCount+=1; // first
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
                            accessCount+=1; // first
		return first == null;
	}

	@Override
	public void insert(T elem, int n) {
		IndexOutOfBound(n);
		Node<T> before = _get(first, 0, n);     accessCount+=1; // first
		Node<T> between = new Node<>(elem);
		Node<T> after = before.getNext();       accessCount+=1; // first
		before.next(between);
		between.next(after);
		this.length += 1;
	}

	@Override
	public T first(){
		return getFirst().getElem();
	}
	public Node<T> getFirst() {                    accessCount+=1; // first
		return this.first;
	}

	@Override
	public T get(int index) {
		IndexOutOfBound(index);                 accessCount+=1; // first
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
                        
                                                accessCount+=1; // .getNext()
			return _get(node.getNext(), currentIndex, givenIndex);
		}
		return node;
	}

	@Override
	public int hashCode() {
		final int inter_universal_teichmueller_prime = 31;
		int result = 1;
		result = inter_universal_teichmueller_prime * result + ((first == null) ? 0 : first.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Lists HashCode: " + this.hashCode() + " with length: "
				+ this.length;
	}
}
