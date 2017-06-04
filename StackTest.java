import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.rules.ExpectedException; 
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.Throwable;
/**
 * Stack test class
 * 
 *<pre>
 * Course: ADEV - 3001
 * Assignment:  #3
 * Date Created: 03/12/2017
 *
 * Revison Log
 * Who                 When           Reason
 * ------------------  -------------  ----------------
 *
 *</pre> 
 * @author Kyle Cherewyk
 * @version 1.00
 */
public class StackTest
{
    // instance variables to be used. 
    Class<?> innerClass = null ; 
    Object innerNode = null;
    Field privateField = null; 
    Stack<String> stack = null;
    Field head = null;
    Constructor<?> constructor; 
    /**
     * Default constructor for test class StackTest
     */
    public StackTest()
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
     * need to check if this is accesing the correct privates. 
     */
    @Test
    @SuppressWarnings ("unchecked")
    public void testNoArgStackConstructor() throws  ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,InvocationTargetException,NoSuchFieldException
      {  
        stack = new Stack<>();
        
        //grad the head field 
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        
        //cast the object node as an Object. 
        Object head = (Object)privateField.get(stack);        
        assertNull(head);      
        
        //Extract  and test "size"
        privateField = Stack.class.getDeclaredField("size");
        privateField.setAccessible(true);
        int size1 = (int)privateField.get(stack);
        assertEquals(0, size1);
        
    }
    
    /**
     * test the getSize method of the stack
     *  expected result 0 
     */
   @Test 
   public void testGetSize(){
      Stack<String> testStack = new Stack<String>();       
      assertEquals(0 , testStack.getSize());              
   }
   
    /**
     * create a stack the add a new node to the stack. 
     * Then use prevously tested getSize() to find size of stack 
     * expected result 1. 
     */
   @Test
   public void testPush(){
      Stack<String> testStack = new Stack<String>(); 
      testStack.push("test data"); 
      assertEquals(1, testStack.getSize());      
    }
   
    /**
     * create new stack and push a new node on top it. 
     * call the top method and test the result 
     * expected result is "test data".
     */
   @Test
   public void testTop1(){
     Stack<String> testStack = new Stack<String>(); 
     testStack.push("test data");
     assertEquals("test data" , testStack.top()); 
   }
    
   /**
    * create new empty stack 
    * call the top method and test the result 
    * expected result is an exception 
    */
   @Test (expected = NoSuchElementException.class)
   public void testTop2(){
     Stack<String> testStack = new Stack<String>();    
     testStack.top();     
    }
   
    /**
     * create new stack and push a new node on top it.
     * call pop method to remove the stack item 
     * then test for returned element and size of stack. 
     */
   @Test
   public void testPop1(){
     Stack<String> testStack = new Stack<String>(); 
     testStack.push("test data");
     assertEquals("test data", testStack.pop());
     assertEquals(0, testStack.getSize());     
    }
   
     /**
     * create a new empty stack.
     * call pop method to remove the stack item 
     * expected result is an exception.  
     */
   @Test (expected = NoSuchElementException.class)
   public void testPop2(){
     Stack<String> testStack = new Stack<String>();     
      testStack.pop();
    }
   
    /**
     * create a new empty stack 
     * then run the isEmpty method 
     * expected result is true. 
     */
   @Test 
   public void testIsEmpty1(){
       Stack<String> testStack = new Stack<String>(); 
       assertTrue(testStack.isEmpty());       
    }
   
    /**
     * create a new stack add 1 item 
     * call isEmpty method 
     * expected result if false. 
     */
   @Test 
   public void testIsEmpty2(){
       Stack<String> testStack = new Stack<String>();
       testStack.push("test data");
       assertFalse(testStack.isEmpty());       
    }
   
   /**
    * test the inner Node classes constructor
    */
   @Test
   public void testInnerNodeConstructor() throws ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,InvocationTargetException,NoSuchFieldException
   {
    // create a new class 
    stack = new Stack<String>(); 
    innerClass = Class.forName("Stack$Node");
    
    // build the parameters array to pass to the inner class
    Class[] parameters = new Class[3];
    parameters[0] = Stack.class;
    parameters[1] = Object.class; 
    parameters[2] = innerClass;
    
    // construct the inner class
    constructor = innerClass.getDeclaredConstructor(parameters);
    constructor.setAccessible(true);
    
    // create a new instance of the inner node with parmeters
    Object nodeObject = constructor.newInstance(stack ,"I don't like relfection", null);
    
    // grab the element field in the class. 
    Field elementField = innerClass.getDeclaredField("element");
    elementField.setAccessible(true); 
    // then set the data in the element field to a string value and assert that it is the same 
    // as the one entered. 
    String element = (String)elementField.get(nodeObject);
    assertEquals("I don't like relfection",element); 
    
    // acccess the previous field. 
    Field field = innerClass.getDeclaredField("previous");
    field.setAccessible(true);  
    
    // set the previous field value to a generic object and assert it as null
    Object previous = (Object)field.get(nodeObject); 
    assertNull(previous);
   }
   
   /**
    * test the inner classes getElement method using reflection
    */
   @Test
   public void testInnerNodeGetElement() throws ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,InvocationTargetException,NoSuchFieldException
   {       
    stack = new Stack<String>(); 
    innerClass = Class.forName("Stack$Node");
    
    // build the parameters array to pass to the inner class
    Class[] parameters = new Class[3];
    parameters[0] = Stack.class;
    parameters[1] = Object.class; 
    parameters[2] = innerClass; 
    
    // construct the inner class
    constructor = innerClass.getDeclaredConstructor(parameters);
    constructor.setAccessible(true);  
    
    // construct new inner node object. 
    Object nodeObject = constructor.newInstance(stack,"reflection is confusing" , null);     
    
    // grab the element parameter in the node. 
    Field elementField = innerClass.getDeclaredField("element");
    elementField.setAccessible(true);
    
    // grab the value in the element field. 
    String element = (String)elementField.get(nodeObject);
     
    // access the getElement method then pass it to a method object.
    Method getElement = innerClass.getDeclaredMethod("getElement");
    getElement.setAccessible(true); 
    
    // invoke the method object and assert that the value in tbe 
    assertEquals(element,getElement.invoke(nodeObject));    
   }
   
   /**
    * test the Inner nodes getPrevious method using relfection
    */
   @Test
   public void testInnerNodeGetPrevious() throws ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,InvocationTargetException,NoSuchFieldException
   {       
    stack = new Stack<String>(); 
    innerClass = Class.forName("Stack$Node");
    Class[] parameters = new Class[3];
    parameters[0] = Stack.class;
    parameters[1] = Object.class; 
    parameters[2] = innerClass; 
    
    constructor = innerClass.getDeclaredConstructor(parameters);
    constructor.setAccessible(true);  
    
    // construct new inner node object. 
    Object nodeObject = constructor.newInstance(stack,"reflection is confusing" , null);     
    Object nextNodeObject = constructor.newInstance(stack,"reflection is confusing v2.00" ,nodeObject);
       
    //grab the previous parameter. 
    Field field = innerClass.getDeclaredField("previous");
    field.setAccessible(true);
    Object previous = (Object)field.get(nodeObject);
    
    // access the getPrevious, then assign it to a method to be invoked.  
    Method getPrevious = innerClass.getDeclaredMethod("getPrevious");
    getPrevious.setAccessible(true); 
    
    // assert that the previous element is the same as the one returned from the getPrevious method. 
    assertEquals(previous ,getPrevious.invoke(nodeObject)); 
    
    
   }
}
