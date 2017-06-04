/**
 * Item 
 * Assignment 4
 * Course ADEV -3001
 * Date Created: 04/03/2017
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
public class Item
{
    private String name;
    private final int POLYNOMIAL_VALUE = 31; 
    /**
     * Constructor for objects of class Item
     */
    public Item(String name)
    {
        this.name = name;       
    }
    
    /**
     * converts the name of the item to add a hash code 
     * @return int - hashcode
     */
    @Override
    public int hashCode(){
        char[] nameCHR = name.toCharArray();
        int hashValue = 0; 
        for(int i = 0 ; i < nameCHR.length ; i++){
           hashValue = hashValue + (int)(nameCHR[i]*Math.pow(POLYNOMIAL_VALUE,i));
        }
        return Math.abs(hashValue);
    }
    
    /**
     * 
     * @param Object - vaule being compared. 
     * @return Boolean - returns boolean if items are equal or not 
     */
    @Override
    public boolean equals(Object o){
        if(this == o)
        {
            return true; 
        }
        if(o == null|| this.getClass() != o.getClass())
        {
          return false; 
        }
        Item item = (Item) o;        
        return this.name.equals(item.getName());
    }    
    
    /**
     * returns the name of an Item
     * @return String - item name
     */
    public String getName(){
        return name;  
    }
    
    /**
     * returns the string representation of the item
     * @return String - the item
     */
    @Override
    public String toString(){
        return name ; 
    }    
}
