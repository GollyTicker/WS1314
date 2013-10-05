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
        
        // tests for an empty list
        assertNull(mll.head());
        assertTrue(mll.isempty());
        assertEquals(mll.length(),0);
        
        // 15 is added into the list. the list isn't empty anymore and has the length 1
        // head() removes 15 and return it again
        mll.cons(15);
        assertEquals(mll.first().intValue(),15);
        assertFalse(mll.isempty());
        assertEquals(mll.length(),1);
        assertEquals(mll.head().intValue(),15);
        
        // the list should be empty again
        assertNull(mll.head());
        assertTrue(mll.isempty());
        assertEquals(mll.length(),0);
        
        // add 15 and 13 to the list
        mll.cons(15);
        mll.cons(13);
        
        // 13 was added most recently, therefore it's on the first position
        assertEquals(mll.first().intValue(),13);
        
        // The list now look like this:
        // Index 0: 13
        // Index 1: 15
        assertEquals(mll.get(0).intValue(),13);
        assertEquals(mll.get(1).intValue(),15);
        
        // the list has two elements now
        assertFalse(mll.isempty());
        assertEquals(mll.length(),2);
        
        
        mll.insert(14, 0);          // insert(elem,n) puts the elem at the index (n+1) 
        // The list now looks like this:
        // 0: 13
        // 1: 14
        // 2: 15
        assertEquals(mll.first().intValue(),13);
        assertEquals(mll.get(0).intValue(),13);
        assertEquals(mll.get(1).intValue(),14);
        assertEquals(mll.get(2).intValue(),15);
        
        // The list has three elements
        assertFalse(mll.isempty());
        assertEquals(mll.length(),3);
        
        // throw the first element out
        assertEquals(mll.head().intValue(),13);
        
        // The list now looks like this:
        // 0: 14
        // 1: 15
        assertEquals(mll.first().intValue(),14);
        assertEquals(mll.get(0).intValue(),14);
        assertEquals(mll.get(1).intValue(),15);
        
        // list has two elements now
        assertFalse(mll.isempty());
        assertEquals(mll.length(),2);
        
        
        // remove both elements
        assertEquals(mll.head().intValue(),14);
        assertEquals(mll.head().intValue(),15);
        
        // list is empty again
        assertNull(mll.head());
        assertTrue(mll.isempty());
        assertEquals(mll.length(),0);
        
    }
    
    @Test (expected = java.lang.NullPointerException.class)
    public void tests_negative1(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.first();        // there is no first element of an empty list
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative2(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.cons(15);
        mll.insert(0, 1);   // the (n+1)th position for the insertion is empty!
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative3(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.cons(15);
        mll.insert(0, -1);   // the index is -1 !
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative4(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.cons(15);
        mll.insert(0, 2);   // the index is out of bounds
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative5(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.cons(15);
        mll.get(1);         // out of bounds
    }
    
    @Test (expected = java.lang.IndexOutOfBoundsException.class)
    public void tests_negative6(){
        MLinkedList<Integer> mll = new MLinkedList<>();
        mll.get(0);         // empty list
    }
}