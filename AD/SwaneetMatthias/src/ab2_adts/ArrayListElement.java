package ab2_adts;
/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class ArrayListElement {
	private int j;
	private double value;
	
	public ArrayListElement(int j, double value){
		this.j = j;
		this.value = value;
	}
	
	public int getJ(){
		return j;
	}
	
	public double getValue(){
		return value;
	}
}
