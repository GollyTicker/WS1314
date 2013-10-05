/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ab1_test;

import ab1_adts.ListImpl.MLinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Swaneet
 */
public class ListImplTestingFunctionality {

    public ListImplTestingFunctionality() {
    }

    @Test
    public void tests_positive() {
        MLinkedList<Integer> mll = new MLinkedList<>();
        
        // ohne ein eingefuegtes Element, gibt es kein zurueckzulieferndes Element
        assertNull(mll.head());
        assertTrue(mll.isempty());
        assertEquals(mll.length(),0);
        
        // 15 wird eingef��gt und muss dann sofort wieder rauskommen.
        // au��erdem ist die Liste dabei nicht leer und hat die length 1.
        mll.cons(15);
        assertEquals(mll.first().intValue(),15);
        assertFalse(mll.isempty());
        assertEquals(mll.length(),1);
        assertEquals(mll.head().intValue(),15);
        
        // nun sollte die liste wieder leer sein.
        assertNull(mll.head());
        assertTrue(mll.isempty());
        assertEquals(mll.length(),0);
        
        // 15 und 13 hinzuf��gen
        mll.cons(15);
        mll.cons(13);
        
        // 13 wurde zuletzt hinzugef��gt und ist daher ganz vorne.
        assertEquals(mll.first().intValue(),13);
        
        // Index 0: 13
        // Index 1: 15
        assertEquals(mll.get(0).intValue(),13);
        assertEquals(mll.get(1).intValue(),15);
        
        // Die liste hat zwei Elemente
        assertFalse(mll.isempty());
        assertEquals(mll.length(),2);
        
        
        mll.insert(14, 0);          // Das Element landet an der (n+1)-ten Position
        // Die Liste sieht nun so aus:
        // 0: 13
        // 1: 14
        // 2: 15
        assertEquals(mll.first().intValue(),13);
        assertEquals(mll.get(0).intValue(),13);
        assertEquals(mll.get(1).intValue(),14);
        assertEquals(mll.get(2).intValue(),15);
        
        // Die liste hat drei Elemente
        assertFalse(mll.isempty());
        assertEquals(mll.length(),3);
        
        // wirf das vorderste Element raus
        assertEquals(mll.head().intValue(),13);
        
        // Die Liste sieht nun so aus:
        // 0: 14
        // 1: 15
        assertEquals(mll.first().intValue(),14);
        assertEquals(mll.get(0).intValue(),14);
        assertEquals(mll.get(1).intValue(),15);
        
        // Die liste hat zwei Elemente
        assertFalse(mll.isempty());
        assertEquals(mll.length(),2);
        
        
        // wirf beide vordersten Elemente wieder raus
        assertEquals(mll.head().intValue(),14);
        assertEquals(mll.head().intValue(),15);
        
        // die liste ist wieder leer
        assertNull(mll.head());
        assertTrue(mll.isempty());
        assertEquals(mll.length(),0);
        
    }
    
    @Test (expected = java.lang.NullPointerException.class)
    public void tests_negative1(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.first();        // es gibt kein erstes Element einer leeren Liste
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative2(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.cons(15);
        mll.insert(0, 1);   // die (n+1)-te Position ist leer! - siehe doc
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative3(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.cons(15);
        mll.insert(0, -1);   // der Index ist -1 !
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative4(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.cons(15);
        mll.insert(0, 2);   // der Index ist au��erhalb der Listengr����e
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative5(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.cons(15);
        mll.get(1);         // es gibt kein Element dieses Indexes
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative6(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.get(0);         // es gibt kein Element dieses Indexes
    }
}