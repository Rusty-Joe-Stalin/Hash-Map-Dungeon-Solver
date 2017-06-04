import java.lang.reflect.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Item Test
 * Assignment 4
 * Course ADEV -3001
 * Date Created: 03/29/2017
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
public class ItemTest
{
    /**
     * Default constructor for test class ItemTest
     */
    public ItemTest()
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
     * test constructor 
     */
    @Test
    public void testItemSingleArgConstructor()throws NoSuchFieldException ,ClassCastException, IllegalArgumentException, IllegalAccessException{
        Item stuff = new Item("sword");
        Field privateField;         
        privateField = Item.class.getDeclaredField("name");
        privateField.setAccessible(true);
        
        String name  = (String) privateField.get(stuff);
        assertEquals("sword",name);
        
        privateField = Item.class.getDeclaredField("POLYNOMIAL_VALUE");
        privateField.setAccessible(true); 
        
        int value = (int)privateField.get(stuff); 
        assertEquals(31 , value);     
    }
    /**
     * test the hash code method 
     */
    @Test
    public void testHashCode(){
      Item newItem = new Item("1"); 
      assertEquals( 49 , newItem.hashCode());
    }
    
    /**
     * test equals method 1 expected result true 
     */
    @Test
    public void testEquals1(){
      Item item1 = new Item("hi"); 
      Item item2 = new Item("hi"); 
      
      assertTrue(item1.equals(item2)); 
      
    } 
    /**
     * test equals method with different strings expected result false 
     */
    @Test
    public void testEquals2(){
      Item item1 = new Item("hi"); 
      Item item2 = new Item("hihi");      
      assertFalse(item1.equals(item2));      
    }
    
     
    /**
     * test equals method with 2 different objects expected result false 
     */
    @Test
    public void testEquals3(){
      Item item1 = new Item("hi"); 
      ItemValue item2 = new ItemValue(1);      
      assertFalse(item1.equals(item2));      
    }   
    /**
     * test get name expected result "hi"
     */
    @Test
    public void testGetName(){
      Item item1 = new Item("hi");
      assertEquals("hi", item1.getName());
    }
    /**
     * test to string 
     */
    @Test
    public void testToString(){
        Item item1 = new Item("hi");
        assertEquals("hi", item1.toString());
    }
}
