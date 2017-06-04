import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Hash Map  
 * Assignment 4
 * Course ADEV -3001
 * Date Created: 04/03/2017
 * <pre>
 * Revison Log 
 * Who              When            Reason 
 * ------------     -------------   --------------
 * kyle             04/14/2017      changed findMatchingBucket to find if spot is not avaliable as well
 * 
 * 
 * </pre> 
 * @author Kyle Cherewyk
 * @version 1.03
 * 
 */
public class HashMap<K,V> implements Map<K,V> 
{
    private int size;
    private double loadFactor; 
    private int threshold; 
    private Entry<K,V>[] table; 
    private Entry<K,V> available; 
    private static final int DEFAULT_CAPACITY = 11;
    private static final double DEFAULT_LOAD_FACTOR = .75;    
    
    /**
     * No arg Constructor for objects of class HashMap
     */
    public HashMap()
    {
        this(DEFAULT_CAPACITY , DEFAULT_LOAD_FACTOR); 
    }

    /**
     * Single arg  Constructor for objects of class HashMap
     * @param int starting size of hash map 
     */
    public HashMap(int initialCapacity)
    {
        this(initialCapacity ,DEFAULT_LOAD_FACTOR); 
    }

    /**
    * Double arg  Constructor for objects of class HashMap
    * @param int starting size of hash map
    * @param double load factor percentage of the size used for threshold 
    */
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity ,double loadFactor)
    {
        if (loadFactor > 1 || loadFactor <0)
        {
            throw new IllegalArgumentException("load factor must be between 1 and 0 ");  
        }
        size = 0;
        this.loadFactor = loadFactor;
        table = (Entry<K,V>[])new Entry[initialCapacity];
        available = new Entry<K,V>(null,null);
        threshold = (int)(table.length * loadFactor); 
    }

    /**
     * returns size 
     * 
     * @return int size
     */
    public int getSize(){
        return size;
    }

    /**
     * returns a boolean determining if the HashMap is empty. 
     * @return boolean 
     */    
    public boolean isEmpty(){
        return size==0; 
    }

    /**
     * clears the map when called 
     */
    public void clear()
    {
        size = 0; 
        for(int i = 0 ; i< table.length;i++)
        {
           table[i] = null; 
        }
    }

    /**
     * returns the key of an entry when called 
     * @param K key 
     * @return value 
     */
    public V get(K key){
        int foundPosition = findMatchingBucket(key); 
        V value =  null; 
        if ( foundPosition >= 0) {
            value = table[foundPosition].getValue();   
        }
        return value;
    }

    /**
     * replaces an item in the map
     * @param K key input key to find position 
     * @param V value being added
     */
    public V put(K key,V value){
        V obj= null;
         if(key == null || value== null){
            throw new IllegalArgumentException("key or value can not be null"); 
        }
        obj = remove(key);
        if(size>threshold){
           rehash();
        }   
        int index  = findBucket(key);
        table[index] = new Entry<K,V >(key,value);
        size++; 
        return obj;
    }

    /**
    * removes an entry when called 
    * @params K key to find position. 
    * @returns the removed value from the map
    */   
    public V remove(K key){
        int position = findMatchingBucket(key); 
        V placeHolder= null;
        if(position != -1){
         placeHolder = table[position].getValue(); 
         table[position] = available;
         size--;
        }
        return placeHolder;       
    }

    /**
     * Provides an Iterator of keys when called. 
     * @return Iterator of the key in the has map
     */
    public Iterator keys(){
        return new KeyIterator<K,V>(table); 
    }

    /**
     * provides an Iterator of values when called  
     * @return Iterator of values in hash map
     */
    public Iterator values(){
        return new ValueIterator<K,V>(table); 
    }

    /**
     * Will return the hased key value of an entry. 
     * @param K key
     * @return int hashed keyvalue
     */
    private int findBucket(K key){
        int position =0 ;
        int hashCode = key.hashCode();
        for(int i=0 ;i< table.length ; i++){
           position = (Math.abs(hashCode)+i)%table.length ;                       
           if(table[position] == available || table[position]== null||( table[position].getKey().equals(key) && table[position] != null)){
                return position; 
           }
           if( i == table.length -1 ){
                throw new NoSuchElementException("key does not exist ");
           }   
        }
        return position;
    }

    /**
     * Find the mathcing bucket of an inputed key value
     * @param K key 
     * @return int bucket hashed value
     **/
    private int findMatchingBucket(K key) {     
        int position;
        int hashCode = key.hashCode();        
        for(int i = 0 ; i < table.length-1; i++)
        {
           position = (Math.abs(hashCode)+i) % table.length;
           if(table[position] != null &&table[position] != available &&  table[position].getKey().equals(key) ){
                return position;
           }   
        } 
        return -1; 
    }

    /**
     * test the size of the array then add more capacity to it if needed
     * @return int prime number.
     */
    private int resize(){
        int newSize = table.length*2 + 1 ;
        boolean isPrime = false ; 
        int sqr =0;
        int mod= 0; 
        do {
            sqr = (int)Math.sqrt(newSize);
            for(int i = 3 ; i<= sqr ;i++){
                mod = newSize % i; 
                if(mod ==0 || i == sqr && mod ==0){
                    break; 
                }
             }
            if(mod == 0)
            {               
                newSize += 2;
            }else{
                isPrime = true;             
            }
          }while(!isPrime);
        return newSize; 
    }

    /**
    * determine new array size by calling resize()
    * creates a new array at the new prime number size() 
    */
    @SuppressWarnings("unchecked")
    private void rehash(){
        Entry<K,V>[] oldTable = this.table;
        this.size = 0;
        table = (Entry<K,V>[])new Entry[resize()];        
        for(int i = 0 ; i < oldTable.length;i++){            
            if(oldTable[i]!= null && oldTable[i] != available){ 
              put(oldTable[i].getKey() , oldTable[i].getValue());
             }
        }
        threshold = (int)(table.length * loadFactor);
    }

    /**
     * Key Iterator class 
     * Assignment 4
     * Course ADEV -3001
     * Date Created: 04/06/2017
     * <pre>
     * Revison Log 
     * Who              When            Reason 
     * ------------     -------------   --------------
     * 
     * 
     * </pre> 
     * @author Kyle Cherewyk
     * @version 1.03
     * 
     */
     private class KeyIterator<K,V> implements Iterator<K>{
        private Entry<K,V>[] values; 
        private int next;
        private Entry<K,V> nextInstance; 
        /**
         * keys iterator  constructor
         */        
        public KeyIterator( Entry<K,V>[] values){
           this.values = values;
           next = 0; 
           do{
               nextInstance= values[next];  
               if(nextInstance != null && nextInstance.getKey() != null ){
                  break;  
               }
               next++; 
           }while(next<values.length); 
        }        
        /**
         * if the next value is not null 
         * @return boolean 
         */
        public boolean hasNext(){
            return nextInstance != null; 
        }       
        /**
         * return the next key in the list 
         * @return K key returns the key of the current entry
         */
        public K next(){
            Entry<K, V> current = nextInstance; 
            next++; 
            while(next <= values.length-1){
               nextInstance= values[next];  
               if(nextInstance != null && nextInstance.getKey() != null ){
                  break;  
               }
               next++; 
            }
            return current.getKey(); 
        } 
    } 

    /**
     * Value Iterator class 
     * Assignment 4
     * Course ADEV -3001
     * Date Created: 04/06/2017
     * <pre>
     * Revison Log 
     * Who              When            Reason 
     * ------------     -------------   --------------
     * 
     * 
     * </pre> 
     * @author Kyle Cherewyk
     * @version 1.03
     * 
     */    
    private class ValueIterator<K,V> implements Iterator<V>{
        private Entry<K,V>[] values; 
        private int next;
        private Entry<K,V> nextInstance; 
        /**
         * set the next instance of the entry in the table array when constructed, 
         */
        public ValueIterator( Entry<K,V>[] values){
           next = 0; 
           this.values = values;
           do{
               nextInstance= values[next];  
               if(nextInstance != null && nextInstance.getKey() != null ){
                  break;  
               }
               next++; 
           }while(next<values.length); 
        }        
        /**
         * if the next value is not null 
         * @return boolean 
         */
        public boolean hasNext(){
            return nextInstance != null; 
        }       
        /**
         * return the valu of the next entry 
         * @return K key returns the key of the current entry
         */
        public V next(){
            Entry<K, V> current = nextInstance; 
            next++; 
            while(next <= values.length){
               nextInstance= values[next];  
               if(nextInstance != null && nextInstance.getKey() != null ){
                  break;  
               }
               next++; 
            }
            return current.getValue(); 
        } 
    } 

    /**
     * Entry class 
     * Assignment 4
     * Course ADEV -3001
     * Date Created: 04/06/2017
     * <pre>
     * Revison Log 
     * Who              When            Reason 
     * ------------     -------------   --------------
     * 
     * 
     * </pre> 
     * @author Kyle Cherewyk
     * @version 1.03
     * 
     */
    private class Entry<K,V > {        
        private K key;        
        private V value;        
        /**
         * 2 arg constrictor for entry
         */
        public Entry(K key , V value){
            this.key = key; 
            this.value = value; 
        }        

        /**
         * returns a key of an entry when called. 
         * @return K key
         */
        public K getKey(){
            return key;   
        }        

        /**
         * return value of an element when called 
         * @return value 
         */
        public V getValue(){
            return value;
        }

        /**
         * sets the value of an entry when called
         * @param V value
         */
        public void setValue(V value )
        {
            this.value = value; 
        }

        /**
         * reutrns a string representing an entry 
         * @return the String of an entry when called
         */
        public String toString()
        {
            return key.toString()+" "+value.toString();  
        }        
    }
}
