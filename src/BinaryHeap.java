package src;
import java.util.*;
import java.lang.*;
import java.io.*;

//Using Mark Allen Weiss' BinaryHeap implementation.
public class BinaryHeap<T extends Comparable<? super T>>
{
    //Gabriel Roa's class for creating a heap using an existing array.
    public BinaryHeap(T[] array){
      this(array.length);
      for (int i = 0; i < array.length; i++){
        this.insert(array[i]);
      }
    }
    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = new Comparable[ capacity + 1 ];
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     * @exception Overflow if container is full.
     */
    public void insert( Comparable x )
    {
        if( isFull( ) )
            System.out.println("Fuck man.");

            // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or null, if empty.
     */
    public Comparable findMin( )
    {
        if( isEmpty( ) )
            return null;
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or null, if empty.
     */
    public Comparable deleteMin( )
    {
        if( isEmpty( ) )
            return null;

        Comparable minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Test if the priority queue is logically full.
     * @return true if full, false otherwise.
     */
    public boolean isFull( )
    {
        return currentSize == array.length - 1;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 100;

    private int currentSize;      // Number of elements in heap
    private Comparable [ ] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
       int child;
       Comparable tmp = array[ hole ];

       for( ; hole * 2 <= currentSize; hole = child )
       {
           child = hole * 2;
           if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
              child++;
           if( array[ child ].compareTo( tmp ) < 0 )
               array[ hole ] = array[ child ];
            else
               break;
        }
      array[ hole ] = tmp;
    }


    //Gabriel Roa's heapsort class.
    public void heapsort(){
      Comparable[] tempArray = new Comparable[this.array.length];
      for (int i = 0; i < this.array.length; i++){
        tempArray[i] = this.deleteMin();
      }

      array = tempArray;
    }

    //Gabriel Roa's printHeap class for testing purposes.
    public void printHeap(){
      System.out.println("Printing Heap");
      System.out.println("The size of the heap array is currently: " + array.length);
      for(Comparable a : this.array){
        System.out.println(a);
      }
    }

    public Comparable[] getHeapArray(){
      return this.array;
    }


}
