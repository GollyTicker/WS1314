package ab1_adts.ListImpl;

public interface IList<T> {

    
        // fügt vorne das Element hinzu
	void cons(T elem);
        
        // entfernt das vorderste Element
        // und gibt es zurück
	T head();
        
        // get ist nicht im ursprünglichen Interface vorhanden gewesen,
        // stellte sich aber als nützlich herraus
	T get(int index);
        //throwt ein Exception beim ungültigen index
        // der Index geht von 0 bis (length()-1)
        
        // gibt das vorderste Element zurück
	T first();
        
        // Wie viele Elemente hat die Liste?
	int length();
        
        // Hat die Liste irgendwelche Elemente?
	boolean isempty();
        
        // fügt das gegebene Element zwischen der
        // n-ten und (n+1)-ten Position hinzu.
	void insert(T elem, int n);
        // Falls es kein (n+1)-tes Element gibt,
        // oder der index in sonstiger weise ungültig ist,
        // gibt es ein Exception

}
