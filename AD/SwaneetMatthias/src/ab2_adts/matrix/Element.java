package ab2_adts.matrix;

/**
 * 
 * @author Swaneet Sahoo, Matthias Nitsche
 */
public class Element {
	private int i;
	private int j;
	private double value;
	
	public Element(int i, int j, double value){
		this.i = i;
		this.j = j;
		this.value = value;
	}
	
	public int getI(){
		return i;
	}
	
	public int getJ(){
		return j;
	}
	
	public double getValue(){
		return value;
	}
}
