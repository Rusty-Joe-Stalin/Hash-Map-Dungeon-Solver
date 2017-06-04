import java.util.Iterator; 
/**
 * Map interface
 * Assignment 4
 * Course ADEV -3001
 * Date Created: 04/03/2017
 * <pre>
 * Revison Log 
 * Who              When            Reason 
 * ------------     -------------   --------------
 *
 * </pre> 
 * @author Kyle Cherewyk
 * @version 1.03
 * 
 */
public interface Map <K ,V >
{
   
    public int getSize();
       
    public boolean isEmpty();
        
    public void clear();
    
    public V get(K key);
    
    public V put(K key, V value);
    
    public V remove(K key);
    
    public Iterator keys();
    
    public Iterator values(); 
}
