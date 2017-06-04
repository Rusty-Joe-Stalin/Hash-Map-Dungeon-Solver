import java.util.NoSuchElementException;
/**
 * LinkedList
 * Assignment 4
 * Course ADEV -3001
 * Date Created: 03/29/2017
 * <pre>
 * Revison Log 
 * Who              When            Reason 
 * ------------     -------------   --------------
 * Kyle C           02/06/2017       add methods to link list for mile stone 2
 * Kyle C           03/29/2017       added Node as an inner class for assignment 4 milestone 1 
 * </pre> 
 * @author Kyle Cherewyk
 * @version 1.03
 * 
 */
public class LinkedList <E extends Comparable<E>>
{
    private Node<E> head;
    private Node<E> tail;
    private int size;
    /**
     *LinkedList constructor creates a new link list when called.
     */
    public LinkedList(){
        clear();
    }

    /**
     *Adds a new node to the link list
     *@param E data - data being added to the list
     */
    public boolean add(E data){
        // useses the private linkHead method to add data to the list.
        linkHead(data);
        return head != null;
    }

    /**
     * Clears the list when called
     *
     */
    public void clear(){
        // set head and tail to null and size to 0.
        this.head = null;
        this.tail = null;
        size = 0;
    }

    /**
     *Checks if the list is empty
     *@return boolean depeding on size.
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * When called will retive the size of the list.
     * @return the size of the list
     */
    public int getSize(){
        return size;
    }

    /**
     * Gets the head element when called.
     *
     * @return the selected element in the list.
     * @exception NoSuchElementException throws no such element exception when user tries to get an item from an empty list.
     */
    public E get(){
        if (isEmpty()){
            throw new NoSuchElementException("Exception - cannot return empty element");
        }
        else{
            return head.getElement();
        }
    }

    /**
     * Removes the indicated list object.
     * @return current element from the private unlinkHead method.
     * @exception NoSuchElementException - If list object is empty then NoSuchElementException will be thrown
     *
     */
    public E remove(){
        if(isEmpty()){
            throw new NoSuchElementException("Exception - cannot remove null");
        }
        else{
            return unlinkHead();
        }
    }

    /**
     * Replaces the data of a node in the list.
     * @param E data - data that will overwrite old data.
     * @exception NoSuchElementException an exception if the list if is empty
     */
    public E set(E data){
        if (isEmpty()){
            throw new NoSuchElementException("Exception - cannot set data to a null list");
        }
        else{
            return setData(data ,head);
        }
    }

    /**
     * add New node after a specified node
     * @param Node<E> data - data being added to list
     * @param int position - indicated position
     * @return boolean if addition was valid
     *
     */
    public boolean addAfter(E data , int position){
        validatePosition(position);
        if(position == size){
            linkTail(data);
        }
        else{
            link(data,find(position),find(position).getNext());
        }
        return true;
    }

    /**
     * add new data before the line specified
     * @param E data - data being passed to the
     * @param int position - the indcated position that waht to add before
     * @return boolen  - return true if the add is successfull
     */
    public  boolean addBefore(E data , int position ){
        validatePosition(position);
        if(position == 1){
            linkHead(data);
        }
        else{
            link( data ,find(position).getPrevious(),find(position));
        }
        return true;
    }

    /**
     * add a new node to the linked list at the head
     * @param E data - data being added to the head of the list.
     * @return boolean - return true if valid
     */
    public boolean addFirst(E data){
        linkHead(data);
        return true;
    }

    /**
     * add a new node at the end of the list.
     * @param E data - data being added to the list
     * @return boolean - returns true if data is added
     */
    public boolean addLast(E data){
        if(size == 0){
            linkHead(data);
        }else{
            linkTail(data);
        }
        return true;
    }

    /**
     * return the data in the numeric position specified item in the linked list.
     * @param int position - the position that the node is located.
     * @return Node<E> - returns the node requested.
     *
     */
    public E get(int position){
        validatePosition(position);
        return find(position).getElement();
    }

    /**
     * Return the first data item in the linked list by calling 'Get'
     * @return element - returns the head element.
     */
    public E getFirst(){
        return get();
    }

    /**
     * Returns the last item in the list
     * @return E - the last element in the list
     */
    public E getLast(){
        if(size == 0){
            throw new NoSuchElementException("Exception - List is empty");
        }else{
            return tail.getElement();
        }
    }

    /**
     *Determine where to add a new node to the linked list. The new
     *node is added in the proper location based on the data to be
     *added (the list is kept in ascending sorted order automatically)
     *@param E data - data being inserted into the list.
     *@return boolean  - true when inserted into list.
     */
    public boolean insert(E data){
        if(size ==0 ){
            linkHead(data);
            return true;
        }
        else{
            Node<E> previous = null;
            Node<E> current = this.head;
            // loop through the current list and compare the items then add the data to the list.
            while(current!= null &&current.getElement().compareTo(data) < 0){
                previous = current;
                current = current.getNext();
            }
            if(previous == null){
                linkHead(data);
            }else if(current == null ){
                linkTail(data);
            }else{
                link(data,previous,current);
            }
            return true;
        }
    }

    /**
     * Change the data in the node position
     * number specified. This method does not
     * change node position, it only changes
     * the data the node points to. If the
     * position is outside the boundaries of the
     * linked list an exception is thrown.
     * @param E data - data to replace old data at the indexed position
     * @param int position - indexed positio in the list
     *
     * @return  E old data.
     */
    public E set(E data,int position){
        // validate the position entered.
        validatePosition(position);
        // if the position is at the end of the list link the tail.
        if(position == size){
            return setData(data ,tail);
        }else{
            return setData(data,find(position));
        }
    }

    /**
     * Change the data in the head node. This
     * method does not change node position, it
     * only changes the data the node points to. If
     * there is no head an exception is thrown.
     * @param E data  - data being set
     * @exception NoSuchElementException - throws new exception when list is empty.
     * @return E data - returns old data being replaced.
     */
    public E setFirst(E data){
        if (!isEmpty()){
            return set(data);
        }
        else{
            throw new NoSuchElementException("Exception - List is empty");
        }
    }

    /**
     * Change the data in the head node. This
     * method does not change node position, it
     * only changes the data the node points to. If
     * there is no head an exception is thrown.
     * @param E data  - data being set
     * @exception NoSuchElementException - throws new exception when list is empty.
     * @return data - returns old data being replaced.
     */
    public E setLast(E data){
        if (!isEmpty()){
            return setData(data,tail);
        }
        else{
            throw new NoSuchElementException("Exception - List is empty");
        }
    }

    /**
     * Remove and return the data in the numeric position
     * specified item in the linked list. If the position is outside
     * the boundaries of the linked list an exception is thrown.
     * @param int position - index position
     * @return oldData - returns data being removed
     */
    public E remove(int position){
        validatePosition(position);
        E oldData;
        if(position == 1){
            oldData = unlinkHead();
        }else{
            if(position != size ){
                // if the size is not equal to the position
                // then find the node and unlink it.
                oldData = unlink(find(position));
            }else{
                oldData = unlinkTail();
            }
        }
        // return the old data
        return oldData;
    }

    /**
     * Remove and return the fist data item in the linked list by
     * calling the 'remove'
     * @return - Removed data.
     */
    public E removeFirst(){
        return remove();
    }

    /**
     * remove and return the last item in the linked list.
     * @exception NoSuchElementException - throws exception if list is empty.
     * @return data from the Unlink tail method;
     */
    public E removeLast(){
        // test if the list is empty
        if(tail == null){
            throw new NoSuchElementException("Exception - List is empty");
        } else{
            if ( size == 1){
                return unlinkHead();
            }
            else{
                return unlinkTail();
            }
        }
    }

    /**
     * Determine where to add a new node to the linked list. The
     * new node is to be added after the data value specified.
     * @param E data - data being added
     * @return Boolean - returns true if add was successful
     * @exception NoSuchElementException thrown if current data is null.
     */
    public boolean addAfter(E data , E oldData){
        Node<E> current = find(oldData);
        if(current == null){
            throw new NoSuchElementException("Exception - current node is empty");
        }else
        if (current == this.tail ){
            linkTail(data);
        }
        else{
            link(data ,current,current.getNext());
        }
        return true;
    }

    /**
     * Determine where to add a new node to the linked list. The new
     * node is to be added before the node with the data specified
     *@param E data - data being added
     *@return Boolean - returns true if add was successful
     *@exception NoSuchElementException thrown if current data is null.
     */
    public boolean addBefore(E data , E oldData){
        Node<E> current = find(oldData);
        if (current == null ){
            throw new NoSuchElementException("Exception - current node is empty");
        }
        else if(current == head){
            linkHead(data);
        }else{
            link(data,current.getPrevious(),current);
        }
        return true;
    }

    /**
     * Return the data using a value stored in the linked list. If there isn't
     * any data in the list or the data is not found an exception is thrown.
     * @param E data - data being retrived.
     * @return E data - returns data being requested.
     * @exception NoSuchElementException - thrown if null node is returned
     */
    public E get(E data){
        Node<E> current = find(data);
        if(current == null ){
            throw new NoSuchElementException("Exception - current node is empty");
        }else{
            return current.getElement();
        }
    }

    /**
     * Remove and return the data using a value stored
     * in the linked list. If there isn't any data in the list or
     * the data is not found an exception is thrown.
     * @param E data - data being retrived.
     * @return E data - returns data being removed.
     * @exception NoSuchElementException - thrown if null node is returned
     */
    public E remove(E data){
        Node<E> current = find(data);
        if(current == null ){
            throw new NoSuchElementException("Exception - cannot find node ");
        }else if (current == this.head ){
            return unlinkHead();
        }
        else if( current == this.tail ){
            return unlinkTail();
        }
        return unlink(current);
    }

    /**
     * Change the data on a node using a
     * value stored in the linked list. If there isn't
     * any data in the list or the data is not
     * found an exception is thrown.
     * @param E data - data being retrived.
     * @param E oldData - OldData being used the find index.
     * @return E oldData - returns data orignially stored in the node.
     * @exception NoSuchElementException - thrown if current node is null
     */

    public E set( E data , E oldData){
        Node<E> current = find(oldData);
        if(current == null ){
            throw new NoSuchElementException("Exception - cannot find node ");
        }
        else{
            return setData(data, current);
        }
    }

    /**
     * find data based on the poition of the data it contains
     *@param E data - data used to compare each node
     *@return data - returns the found node.
     */
    private Node<E> find(E data){
        Node<E> current = this.head;
        if(current == null){
            return null;
        }
        while(current!= null && current.getElement().compareTo(data)!= 0){
            current = current.getNext();
        }
        return current;
    }

    /**
     * Overwirtes a node when called
     * @param E  passed new data
     * @param Node<E> current data
     * @return previous nodes data
     */
    private E setData(E data , Node<E> current)
    {
        // set the old date to a variable
        E oldData = current.getElement();
        //overwrite the old data
        current.setElement(data);
        //return the old data
        return  oldData;
    }

    /**
     * Add a new node to the linked list at the head.
     *
     * @param E data - data being added to the list.
     */
    private void linkHead(E data)
    {
        Node<E> toAdd = new Node<E>(data,null,this.head);
        if (head != null){
            // set the new to the head of the list.
            this.head.setPrevious(toAdd);
        }
        head = toAdd;
        if (isEmpty()){
            // set tail and head to same node if this is the only node in list.
            this.tail = this.head;
        }
        // increase the size by 1
        size ++;
    }

    /**
     * unlinks node at when called
     * @return the current element from the list.
     */
    private E unlinkHead()
    {
        Node<E> current = this.head;
        this.head = head.getNext();
        // sets the previous head to null if the current head is not null.
        if (head != null){
            head.setPrevious(null);
        }
        // reduce the size of the list.
        size--;
        if (isEmpty()){
            // set tail to null if list is empty.
            tail = null;
        }
        // return the current element.
        return current.getElement();
    }

    /**
     * Loop through the list to find the input position
     * @param int position - position to find in list.
     * @return position found in list.
     */
    private Node<E> find(int position)
    {
        // create a node then assign the head position to the node.
        Node<E> current = head;
        for(int i = 2 ;i <= position; i++ ){
            current = current.getNext();
        }
        return current;
    }

    /**
     * links the input dat to the tail pointer when called.
     * @param E data - data to be added.
     */
    private void linkTail(E data)
    {
        // create a new  node
        Node<E> toAdd = new Node<>(data, this.tail , null);
        //point the former tail node the the new node.
        tail.setNext(toAdd);
        // set the new node as the tail pointer
        this.tail = toAdd;
        size++;
    }

    /**
     * Insert a new node into the list, in  between two other nodes.
     * @param E data - data to be added
     * @param Node<E> prevoiuse - previous pointer in list
     * @param Node<E> current - next pointer in the list
     */
    private void link( E data ,Node<E> previous , Node<E> current ){
        Node<E> toAdd = new Node<>(data,previous,current);
        previous.setNext(toAdd);
        current.setPrevious(toAdd);
        size++;
    }

    /**
     * Remove the node at the tail of the linked list and return the data it contains
     * @return E - returns the data that is being removed.
     */
    private E unlinkTail(){
        Node<E> current = this.tail;
        this.tail = tail.getPrevious();
        tail.setNext(null);
        size--;
        return current.getElement();
    }

    /**
     * Remove the specified Node from the linked list(current)
     * @param E current - data that is goign to be removed from the list
     * @return E current - return the data that is being removed.
     */
    private E unlink(Node<E> current){
        Node<E> previous = current.getPrevious();
        Node<E> next = current.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        size--;
        // return the old data being removed.
        return current.getElement();
    }

    /**
     * Validate the position of an element.
     * @param int position - position needing validation
     * @exception NoSuchElementException throws exception if position is out of list range.
     */
    private void validatePosition(int position){
        // check if the position is valid.
        if (position < 1 ){
            throw new NoSuchElementException("Exception - out of list range");
        }else if (position >=1 && position > this.size ){
            throw new NoSuchElementException("Exception - out of list range");
        }
    }

    /**
     * Node class - creates node to store data that will be used in the link list. 
     * <pre>
     * Course: ADEV -3001
     * Assignment:  #4
     * Date Created: 04/03/2017
     * 
     * Revison Log
     * Who                 When           Reason 
     * ------------------  -------------  ----------------
     * Kyle C               03/29/2017     changed to an inner class of linked list
     * </pre>
     * @author Kyle Cherewyk
     * @version 1.02
     */

    private class Node<E>
    {
        private E element;
        private Node<E> previous;
        private Node<E> next;
        /**
         * Default empty Node constructor
         */
        public Node (){
            this( null , null , null); 
        }

        /**
         * Default Node constructor
         * @param E - element being added
         * @param Node<E> - previous Node
         * @param Node<E> - next Node 
         */
        public Node( E element , Node<E> previous, Node<E> next){
            this.element = element;
            this.previous = previous;
            this.next = next;
        }

        /**
         * Returns value of Element
         * @return element
         */
        public E getElement(){
            return element;
        } 

        /**
         * Sets new value of Element
         * @param E - data being set
         */
        public void setElement(E data){
            this.element = data;
        }

        /**
         * Returns value of Previous
         * @return - previous Node
         */
        public Node<E> getPrevious(){
            return previous;
        }

        /**
         * Sets new value of Previous
         * @param Node<E> - previous Node
         */
        public void setPrevious(Node<E> previous){
            this.previous = previous;
        }

        /**
         * Returns value of Next
         * @return - next node
         */
        public Node<E> getNext(){
            return next;
        }

        /**
         * Sets new value of Next
         * @param Node<E> next - set the next pointer.
         */
        public void setNext(Node<E> next){
            this.next = next;
        }
    }
}
