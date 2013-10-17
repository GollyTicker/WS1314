package ad_utils;

public interface ITimeSpace {

	// reset the count to a value
	int accessCount();

	// set the count to a value
	void setAccessCount(int ac);

	// set the current accesscounter to zero
	void resetAccessCount();

	// List, Matrix -> size()
	int memoryUsage();

	// print out the current accessCount
	void printCount();
}
