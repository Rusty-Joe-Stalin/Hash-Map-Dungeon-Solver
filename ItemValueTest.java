import java.lang.reflect.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ItemValue test 
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
public class ItemValueTest
{
    /**
     * Default constructor for test class ItemValueTest
     */
    public ItemValueTest()
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
        ItemValue stuff = new ItemValue(2);
        Field privateField;         
        privateField = ItemValue.class.getDeclaredField("goldPieces");
        privateField.setAccessible(true);
        
        int money  = (int) privateField.get(stuff);
        assertEquals(2,money);      
         
    }    
    
    /**
     * test gold getter 
     */
    @Test 
    public void testGetGold(){
      ItemValue stuff = new ItemValue(2);
      assertEquals(2,stuff.getGoldPieces());
    }
    
    /**
     * test item value toString ()
     */
    @Test
    public void testToString()
    {
       ItemValue stuff = new ItemValue(2);
       assertEquals("2",stuff.toString()); 
    }    
    
}
