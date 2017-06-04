import java.lang.reflect.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HashMapTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HashMapTest
{
    /**
     * Default constructor for test class HashMapTest
     */
    public HashMapTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    
    
     /**
     * test findMatchingBucket 
     * epedcted result is 1 
     */
    @Test
    public void testResize()throws ClassNotFoundException,NoSuchMethodException,InstantiationException,InvocationTargetException,IllegalAccessException,NoSuchFieldException
    {
        HashMap<String, Integer> hashie = new HashMap<>( 4 , .5 );
        Class[] parameters = new Class[2];
        parameters[0] = String.class;
        parameters[1] = Integer.class;
        //Constructor<?> constructor = HashMap.getDeclaredConstructor(parameters);
        //constructor.setAccessible(true);
        //Object hashMapsOBJ = constructor.newInstance(String,Integer); 
        
        Method resize = HashMap.class.getDeclaredMethod("resize");
        resize.setAccessible(true);
        //Class[] params = new Class[1];
        //params[0] = Object.class ; 
        assertEquals(11, resize.invoke(hashie));
        
    }
    /**
    * test the put method   
    **/
    @Test
    public void testPut(){
        HashMap<Item, ItemValue> hashi = new HashMap<Item, ItemValue>();
        ItemValue cash= new ItemValue(5); 
        Item iName = new Item("hi");
        assertNull(hashi.put(iName,cash));
        assertEquals(1, hashi.getSize());
        ItemValue cashMoney= new ItemValue(50);
        assertNotNull(hashi.put(iName,cashMoney));
    }
    /**
    * test is empty expected result is true
    */
    @Test
    public void testIsEmtpy(){
        HashMap<Item, ItemValue> hashi = new HashMap<Item, ItemValue>();
        ItemValue cash= new ItemValue(5); 
        Item iName = new Item("hi");
        assertTrue(hashi.isEmpty());
    }
    /** 
    * test getSize() expected result is 0 
    */
    @Test
    public void testGetSize(){
        HashMap<Item, ItemValue> hashi = new HashMap<Item, ItemValue>();
        ItemValue cash= new ItemValue(5); 
        Item iName = new Item("hi");
        assertEquals(0,hashi.getSize());
    }
    
    /**
     * test the remove method. 
     */
    @Test
    public void testGet(){
        HashMap<Item, ItemValue> hashi = new HashMap<Item, ItemValue>();
        ItemValue cash= new ItemValue(5); 
        Item iName = new Item("hi");
        assertNull(hashi.put(iName,cash));
        assertNotNull(hashi.get(iName));  
        
    }
    
     /**
     * test the remove method. 
     */
    @Test
    public void testRemove(){
        HashMap<Item, ItemValue> hashi = new HashMap<Item, ItemValue>();
        ItemValue cash= new ItemValue(5); 
        Item iName = new Item("hi");
        assertNull(hashi.put(iName,cash));
        assertNotNull(hashi.remove(iName));   
    }
    
    /**
     * test clear 
     */
    @Test
    public void testClear(){
      HashMap<Item, ItemValue> hashi = new HashMap<Item, ItemValue>();
      ItemValue cash= new ItemValue(5); 
      Item iName = new Item("hi");
      hashi.put(iName,cash);
      assertEquals(1,hashi.getSize()); 
      hashi.clear(); 
      assertEquals(0,hashi.getSize()); 
    }
    
    /**
     * test sesize 
     */
    @Test 
    public void testRehash()throws ClassNotFoundException,NoSuchMethodException,InstantiationException,InvocationTargetException,IllegalAccessException,NoSuchFieldException{
        HashMap<Item, ItemValue> hashi = new HashMap<Item, ItemValue>(3 , .5);
        ItemValue cash1= new ItemValue(5); 
        Item iName1 = new Item("hihi"); 
        ItemValue cash2= new ItemValue(5); 
        Item iName2 = new Item("hiHi"); 
        ItemValue cash3= new ItemValue(5); 
        Item iName3 = new Item("hiHI"); 
        
        Class[] parameters = new Class[2];
        parameters[0] = String.class;
        parameters[1] = Integer.class;
        
      
            
        hashi.put(iName1, cash1);
        assertEquals(1, hashi.getSize()); 
        hashi.put(iName2, cash2); 
        assertEquals(2, hashi.getSize());
        hashi.put(iName3, cash3); 
        assertEquals(3, hashi.getSize());
        
        Field privateField; 
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);        
        Object[] table = (Object[])privateField.get(hashi);        
        
        Method resize = HashMap.class.getDeclaredMethod("rehash");
        resize.setAccessible(true);
                
        assertEquals(11, table.length);
            
    }
    
    
    
    
}
