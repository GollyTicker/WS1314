package GraphUtils;

public interface ITimeSpace {

	// reset the count to a value
	int accessCount();

	// set the count to a value
	void setAccessCount(int ac);

	// set the current accesscounter to zero
	void resetAccessCount();

	// print out the current accessCount
	void printCount();
}
