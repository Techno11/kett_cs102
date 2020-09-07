


// Giuseppe Turini
// CS-102, Summer 2020
// Assignment 2

package zais5275.kettering.edu.cs102.assignment_1.TennisDatabase;

// SortedLinkedListNode generic class, implementing the node for the SortedLinkedList class.
class SortedLinkedListNode<T> {

   T item; // Data.
   SortedLinkedListNode<T> next; // Link (reference) to next node.
   
   // Constructor.
   public SortedLinkedListNode( T i ) {
      this.item = i;
      this.next = null;
   }
         
}


