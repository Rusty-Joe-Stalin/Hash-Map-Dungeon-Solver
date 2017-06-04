import java.util.NoSuchElementException;
/**
 * Stack class 
 * <pre> 
 * Course: ADEV -3001
 * Assignment:  #4
 * Date Created: 04/12/2017
 * 
 * Revison Log
 * Who                 When           Reason 
 * ------------------  -------------  ----------------
 * 
 * </pre> 
 * @author Kyle Cherewyk
 * @version 1.00 
 */
public class Stack <E> {
    private Node<E> head;
    private int size;
    /**
     * Default empty Stack constructor
     */
    public Stack(){
        this.head = null; 
        this.size = 0;
    }

    /**
     * Add a new node to the Stack at the head
     * 
     * @param - E element added to the stack. 
     */
    public void push(E element){
        Node<E> toAdd = new Node<E>(element,this.head); 
        head = toAdd; 
        size++;      
    }

    /**
     * Return the first data item on the Stack.
     * Throw an exception if empty.
     * 
     * @return - element on the top of the stack. 
     */
    public E top(){
        if(isEmpty()){
            throw new NoSuchElementException("Exception - empty stack");   
        }
        return head.getElement(); 
    }

    /**
     * Remove the node at the head of the
     * Stack and return the data it contains
     * 
     * @return - element stored in the current node. 
     * @exception - NoSuchElementException thrown when stack is empty 
     */
    public E pop(){
        if (isEmpty()){
          throw new NoSuchElementException("Exception - empty stack"); 
        }
          Node<E> current = head;        
          head = head.getPrevious(); 
          size--;
          return current.getElement();
        }
    
    /**
     * returns the size of the stack. 
     * 
     * @return - int size. 
     */
    public int getSize(){
        return size; 
    }

    /**
     * Returns a boolean to indicate if the Stack is
     * empty or not. True it is, false there are entries. 
     * 
     * @return - boolean if stack is empty.
     */
    public boolean isEmpty(){
        if (size == 0){
           return true; 
        }else{
           return false;
        }
    }
    
         /**
         * Node class - creates node to store data that will be used in the link list. 
         * <pre> 
         * Course: ADEV -3001
         * Assignment:  #4
         * Date Created: 04/12/2017
         * 
         * Revison Log
         * Who                 When           Reason 
         * ------------------  -------------  ----------------
         * 
         * </pre> 
         * @author Kyle Cherewyk
         * @version 1.00 
         */
    private class Node<E> {
        private E element;
        private Node<E> previous;
        
        /**
         * Default Node constructor
         */
        public Node(E element, Node<E> previous) {            
            this.element = element;
            this.previous = previous;
        }
    
        /**
         * Returns value of element
         * @return element value
         */
        public E getElement() {
            return element;
        }
    
        /**
         * Returns value of previous
         * @return Node<E> previous node 
         */
        public Node<E> getPrevious() {
            return previous;
        }
    }    
}
