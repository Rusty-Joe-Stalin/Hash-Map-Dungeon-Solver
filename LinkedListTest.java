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
 * LinkedListTest
 * Assignment 4
 * Course ADEV -3001
 * Date Created: 04/16/2017
 * 
 * Revison Log 
 * Who              When            Reason 
 * ------------     -------------   --------------
 * Kyle C           02/06/2017       add test to link list test 
 * 
 * 
 * @author Kyle Cherewyk
 * @version 1.00
 * 
 */
public class LinkedListTest
{
    //@Rule
    //public ExpectedException exception = ExpectedException.none();
    /**
     * Default constructor for test class LinkedListTest
     */
    public LinkedListTest()
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
        LinkedList<String> list = new LinkedList<>();
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
     * Add new data to linked list if returns true test is valid. 
     */
    @Test
    public void linkListAdd()
    {
        LinkedList<String> list = new LinkedList<>();        
        assertTrue(list.add("test Data"));        
    }

    /**
     * test for an empty list
     */
    @Test
    public void linklistEmptyTest()
    {
        LinkedList<String> list = new LinkedList<>();        
        assertTrue(list.isEmpty());
        list.add("this is Data");        
        assertFalse(list.isEmpty());        
    }

    /**
     * test to determine the size of the list. 
     *  list is empty expected size is 0
     */
    @Test
    public void testGetSize1() 
    {
        LinkedList<String> list = new LinkedList<>(); 
        assertEquals(0,list.getSize()); 
    }

    /**
     * test to determine the size of the list. 
     * data has been added to list expected size is 1
     */
    @Test
    public void testGetSize2() 
    {
        LinkedList<String> list = new LinkedList<>(); 
        list.add("Test Data"); 
        assertEquals(1,list.getSize()); 
    }

    /**
     * test to validate the get method. 
     * expected value is "This is test Data". 
     */
    @Test
    public void testGet()
    {
        LinkedList<String> list = new LinkedList<>();  
        list.add("This is test Data");
        assertEquals("This is test Data" ,list.get());
    }

    /**
    * test the expected exception in the get method.  
    */
    @Test (expected = NoSuchElementException.class)
    public void testGetException() throws NoSuchElementException
    {
        LinkedList list = new LinkedList<>();
        list.clear(); 
        list.get();
    }

    /**
     * test the remove method. 
     * first I added data to the list then ran the remove()
     * expected value is 0
     */
    @Test
    public void testRemove(){
        LinkedList<String> list = new LinkedList<>();  
        list.add("This is test Data");
        list.remove(); 
        assertEquals(0 , list.getSize()); 
    }

    /**
    * remove method exception test 
    * remove() method ran against an empty list. 
    * exception is expected. 
    */
    @Test (expected = NoSuchElementException.class)
    public void testRemoveException() throws NoSuchElementException
    {
        LinkedList list = new LinkedList<>();
        list.remove();
    }

    /**
     * create a new list
     * add new data to the list
     * test if the data was added. 
     * replace the previous data
     * expected value is "This is new test data". 
     */
    @Test 
    public void testSet1(){

        LinkedList<String> list = new LinkedList<>();  

        list.add("This is test Data");

        assertEquals("This is test Data",list.get()); 

        list.set("This is new test data"); 
        assertEquals("This is new test data" , list.get()); 
    }

    /**
     * create a new list
     * add new data to the list. 
     * test if the  old data was returned. 
     */
    @Test 
    public void testSet2(){

        LinkedList<String> list = new LinkedList<>();  

        list.add("This is test Data");

        assertEquals("This is test Data",list.set("This is new test data"));     
    }

    /**
    * run set() against an empty list. 
    * an exception is expected. 
    */
    @Test (expected = NoSuchElementException.class)
    public void testSetException()
    {
        LinkedList<String> list = new LinkedList<>();  
        list.set("this is a exception test");
    }

    /**
     * test the add after method
     */
    @Test 
    public void addAfterTest1() 
    {
        LinkedList<String> list = new LinkedList<>();
        list.add("test data"); 
        assertTrue(list.addAfter("data",1)); 
    }

    /**
    * test the add after method again 
    * expected exception 
    */
    @Test (expected = NoSuchElementException.class)
    public void addAfterTest2() 
    {
        LinkedList<String> list = new LinkedList<>();
        //list.add("test data"); 
        list.addAfter("data",0); 
    }

    /**
     * test the add before method
     */
    @Test 
    public void addBeforeTest1() 
    {
        LinkedList<String> list = new LinkedList<>();
        list.add("test data");       
        assertTrue(list.addBefore("data",1));
    }

    /**
    * test the add before method
    * expceted exception 
    *  
    */
    @Test(expected = NoSuchElementException.class)
    public void addBeforeTest2() 
    {
        LinkedList<String> list = new LinkedList<>();
        list.add("test data"); 
        list.addBefore("data",0); 
    }

    /**
     * test add first method
     */
    @Test
    public void addFirstTest()
    {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.addFirst("data"));     
    }

    /**
     * add last test
     */
    @Test
    public void testAddLast()
    {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.addLast("data")); 
    }

    /**
     * test get method 
     * add 2 items to list, then find and return indicated item 
     */
    @Test
    public void getTest(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data"); 
        list.addAfter("data 1" ,1 ); 
        list.addAfter("data 2" ,2 ); 
        list.addAfter("data 3" ,3 ); 
        list.addAfter("data 4" ,4 ); 
        assertEquals("data 4", list.get(5)); 
    }

    /**
    * test get method 
    * add 2 items to list, then find and return indicated item 
    */
    @Test (expected = NoSuchElementException.class)
    public void getTest2(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data"); 
        list.add("data 2"); 
        list.get(5); 
    }

    /**
     * test get method 
     * add 2 items to list, then find and return indicated item 
     */
    @Test
    public void getFirstTest(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data"); 
        list.add("data2"); 
        assertEquals("data2",list.getFirst()); 
    }

    /**
     * test get method 
     * add 2 items to list, then find and return indicated item 
     */
    @Test
    public void getLastTest(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data"); 
        list.add("data2"); 
        assertEquals("data",list.getLast()); 
    }

    /**
     * test the insert method by creating a list of objects then 
     * insert a object into the list 
     */
    @Test 
    public void testInsert1()
    {
        LinkedList<String> list = new LinkedList<>();
        list.add("data"); 
        list.add("data2");
        assertTrue(list.insert("data3"));
        assertEquals(3 ,list.getSize()); 
    }

    /**
     * insert a item into an empty list 
     */
    @Test 
    public void testInsert2()
    {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.insert("data3"));
    }

    /**
     * test the set method by creating a list then replcing item with new data. 
     * then use the get method to retrive the item to see if it was replaced. 
     */
    @Test
    public void setTest1()
    {
        LinkedList<String> list = new LinkedList<>();
        list.add("data"); 
        list.add("data2");
        assertEquals( "data2" , list.set("New Data",1));
        assertEquals( "New Data" , list.get(1));
    }

    /**
    * expected exception 
    */
    @Test(expected = NoSuchElementException.class)
    public void setTest2()
    {
        LinkedList<String> list = new LinkedList<>();     
        assertEquals( "data2" , list.set("New Data",1));
        assertEquals( "New Data" , list.get(1));
    }

    /**
     * test the setFirst method 
     * create a list with 1 item
     * call method and test if old data is returned then 
     * call get to test first position 
     */
    @Test
    public void testSetFirst(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data"); 
        assertEquals("data",list.setFirst("Replaced data")); 
        assertEquals("Replaced data",list.get(1));
    }

    /**
     * test the setFirst method 
     * create a list with 1 item
     * call method and test if old data is returned then 
     * call get to test first position
     */
    @Test
    public void testSetLast(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data");
        list.add("data1");
        assertEquals("data",list.setLast("Replaced data")); 
        assertEquals("Replaced data",list.get(2));
    }

    /**
     * create a new list and remove the last position
     * then check the list size to see if it was removed. 
     * 
     */
    @Test
    public void testRemoveByPosition(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data");
        list.add("data1");
        list.remove(2); 
        assertEquals(1,list.getSize());
    }

    /**
    * create a new list and remove the last position
    * then check the list size to see if it was removed. 
    * 
    */
    @Test(expected = NoSuchElementException.class)
    public void testRemoveByPositionEx(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data");
        list.add("data1");
        list.remove(3);       
    }

    /**
     * create new list then remove the first item.
     * use the get method to return the first and check if it was removed 
     */
    @Test 
    public void testRemoveFirst(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data");
        list.add("data1");        
        assertEquals("data1" , list.getFirst());
        list.removeFirst();
        assertEquals("data" , list.getFirst());
        assertEquals(1 ,list.getSize()); 
    }

    /**
     * create new list add new 
     */
    @Test
    public void testRemoveLast(){
        LinkedList<String> list = new LinkedList<>();
        list.add("data");
        list.add("data1");        
        assertEquals("data" , list.getLast());
        list.removeLast();
        assertEquals("data1" , list.getLast());
        assertEquals(1 ,list.getSize()); 
    }

    /**
     * test the inner Node classes constructor
     */
    @Test
    public void testInnerNodeConstructor() throws ClassNotFoundException,NoSuchMethodException,InstantiationException,InvocationTargetException,IllegalAccessException,NoSuchFieldException
    {
        // create the Queue class and then grab the inner node class.    
        LinkedList<String> list = new LinkedList<String>(); 
        Class<?> innerClass = Class.forName("LinkedList$Node");
        Class[] parameters = new Class[4];
        parameters[0] = LinkedList.class;
        parameters[1] = Object.class;
        parameters[2] = innerClass;
        parameters[3] = innerClass;

        Constructor<?> constructor = innerClass.getDeclaredConstructor(parameters);
        constructor.setAccessible(true);

        Object nodeObject = constructor.newInstance(list ,"test1", null ,null);
        // grab the element and assert the that the element is equal to the element input.   
        Field elementField = innerClass.getDeclaredField("element");
        elementField.setAccessible(true);      
        String element = (String)elementField.get(nodeObject);
        assertEquals("test1",element); 

        // grab the next field then assert that they are null 
        Field field = innerClass.getDeclaredField("next");
        field.setAccessible(true);            
        Object next = (Object)field.get(nodeObject); 
        assertNull(next);
    }

    /**
     * test the inner nodes getElement method using reflection
     */
    @Test
    public void testInnerNodeGetElement() throws ClassNotFoundException,NoSuchMethodException,InvocationTargetException,InstantiationException,IllegalAccessException,NoSuchFieldException
    {       
        LinkedList<String> list = new LinkedList<String>(); 
        Class<?> innerClass = Class.forName("LinkedList$Node");
        Class[] parameters = new Class[4];
        parameters[0] = LinkedList.class;
        parameters[1] = Object.class;
        parameters[2] = innerClass;
        parameters[3] = innerClass;   
        Constructor<?> constructor = innerClass.getDeclaredConstructor(parameters);
        constructor.setAccessible(true);

        // construct new inner node object. 
        Object nodeObject = constructor.newInstance(list,"reflection is confusing" , null ,null);     

        // grab the element parameter in the node. 
        Field elementField = innerClass.getDeclaredField("element");
        elementField.setAccessible(true);      
        String element = (String)elementField.get(nodeObject);

        Method getElement = innerClass.getDeclaredMethod("getElement");
        getElement.setAccessible(true); 

        // check to make sure the inner element is correct.
        assertEquals(element,getElement.invoke(nodeObject)); 

    }
    /**
     * test the inner nodes getElement method using reflection
     */
    @Test
    public void testInnerNodeSetElement() throws ClassNotFoundException,NoSuchMethodException,InvocationTargetException,InstantiationException,IllegalAccessException,NoSuchFieldException
    {       
        LinkedList<String> list = new LinkedList<String>(); 
        Class<?> innerClass = Class.forName("LinkedList$Node");
        Class[] parameters = new Class[4];
        parameters[0] = LinkedList.class;
        parameters[1] = Object.class;
        parameters[2] = innerClass;
        parameters[3] = innerClass;
        Constructor<?> constructor = innerClass.getDeclaredConstructor(parameters);
        constructor.setAccessible(true);

        // construct new inner node object. 
        Object nodeObject = constructor.newInstance(list,"reflection is confusing", null ,null);     
        
        // get the getElement method to test the "setElement method"
        Method getElement = innerClass.getDeclaredMethod("getElement");
        getElement.setAccessible(true);
        
        Class[] params = new Class[1];
        params[0] = Object.class ; 
        
        // grab the set Element method 
        Method setElement = innerClass.getDeclaredMethod("setElement",params);
        setElement.setAccessible(true);

        // set the element to test1 
        setElement.invoke(nodeObject,"test1");
        
        // grab the element parameter in the node. 
        Field elementField = innerClass.getDeclaredField("element");
        elementField.setAccessible(true);
        String element = (String)elementField.get(nodeObject);

        // check to make sure the inner element is correct.
        assertEquals(element,getElement.invoke(nodeObject)); 

    }
    /**
     * test the inner nodes getNext method using relfection 
     */
    @Test
    public void testInnerNodeGetNext() throws ClassNotFoundException,NoSuchMethodException,InvocationTargetException,InstantiationException,IllegalAccessException,NoSuchFieldException
    {       
        LinkedList<String> list = new LinkedList<String>(); 
        Class<?> innerClass = Class.forName("LinkedList$Node");
        Class[] parameters = new Class[4];
        parameters[0] = LinkedList.class;
        parameters[1] = Object.class;
        parameters[2] = innerClass; 
        parameters[3] = innerClass;   

        Constructor<?> constructor = innerClass.getDeclaredConstructor(parameters);
        constructor.setAccessible(true);  

        // construct new inner node object. 
        Object nodeObject = constructor.newInstance( list,"reflection is confusing" , null ,null);     
        Object nextNodeObject = constructor.newInstance( list,"reflection is confusing" ,nodeObject ,null);

        // grab the element parameter in the node. 
        Field elementField = innerClass.getDeclaredField("element");
        elementField.setAccessible(true);      
        String element = (String)elementField.get(nodeObject);

        //grab the next parameter. 
        Field field = innerClass.getDeclaredField("next");
        field.setAccessible(true);
        Object next = (Object)field.get(nodeObject);

        // assign the getNext method to "getNext"
        Method getNext = innerClass.getDeclaredMethod("getNext");
        getNext.setAccessible(true); 

        // assert if the next node is the same as the one returned from the getNext Method. 
        assertEquals(next ,getNext.invoke(nodeObject)); 
    }

    /**
     * test the inner nodes setNext method using relfection. 
     */
    @Test
    public void testInnerNodeSetNext() throws ClassNotFoundException,NoSuchMethodException,InvocationTargetException,InstantiationException,IllegalAccessException,NoSuchFieldException
    {       
        LinkedList<String> list = new LinkedList<String>(); 
        Class<?> innerClass = Class.forName("LinkedList$Node");
        Class[] parameters = new Class[4];
        parameters[0] = LinkedList.class;
        parameters[1] = Object.class;
        parameters[2] = innerClass;
        parameters[3] = innerClass;

        Constructor<?> constructor = innerClass.getDeclaredConstructor(parameters);
        constructor.setAccessible(true);  

        // construct new inner node object. 
        Object nodeObject = constructor.newInstance(list,"reflection is confusing" , null, null);     
        Object nextNodeObject = constructor.newInstance(list,"reflection is confusing" ,null , null );

        // grab the element parameter in the node. 
        Field elementField = innerClass.getDeclaredField("element");
        elementField.setAccessible(true);      
        String element = (String)elementField.get(nodeObject);

        //grab the next parameter. 
        Field field = innerClass.getDeclaredField("next");
        field.setAccessible(true);
        Object next = (Object)field.get(nodeObject);

        // access the getNext method to use for asserting if the setNext was correct. 
        Method getNext = innerClass.getDeclaredMethod("getNext");
        getNext.setAccessible(true); 

        //assign the getNext method to "setNext"
        Method setNext = innerClass.getDeclaredMethod("setNext",innerClass);
        setNext.setAccessible(true); 

        // call the private setNext method. 
        setNext.invoke(nextNodeObject,nodeObject); 
        // assert if the next node is the same as the one returned from the getNext Method. 
        assertEquals(next,getNext.invoke(nodeObject)); 
    }

    /**
     * test the get previous method in the inner node class. 
     */
    @Test
    public void testInnerNodeGetPrevious() throws ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,InvocationTargetException,NoSuchFieldException
    {       
        LinkedList list = new LinkedList<String>(); 
        Class<?> innerClass = Class.forName("LinkedList$Node");
        Class[] parameters = new Class[4];
        parameters[0] = LinkedList.class;
        parameters[1] = Object.class;
        parameters[2] = innerClass; 
        parameters[3] = innerClass; 

        Constructor<?> constructor = innerClass.getDeclaredConstructor(parameters);
        constructor.setAccessible(true);  

        // construct new inner node object. 
        Object nodeObject = constructor.newInstance(list,"reflection is confusing" , null, null );     
        Object nextNodeObject = constructor.newInstance(list,"reflection is confusing v2.00" ,nodeObject , null );

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
    /**
     * test the inner nodes setNext method using relfection. 
     */
    @Test
    public void testInnerNodeSetPrevious() throws ClassNotFoundException,NoSuchMethodException,InvocationTargetException,InstantiationException,IllegalAccessException,NoSuchFieldException
    {       
        LinkedList<String> list = new LinkedList<String>(); 
        Class<?> innerClass = Class.forName("LinkedList$Node");
        Class[] parameters = new Class[4];
        parameters[0] = LinkedList.class;
        parameters[1] = Object.class;
        parameters[2] = innerClass;
        parameters[3] = innerClass;

        Constructor<?> constructor = innerClass.getDeclaredConstructor(parameters);
        constructor.setAccessible(true);  

        // construct new inner node object. 
        Object nodeObject = constructor.newInstance(list,"reflection is confusing" , null, null);     
        Object nextNodeObject = constructor.newInstance(list,"reflection is confusing" ,null , null );

        // grab the element parameter in the node. 
        Field elementField = innerClass.getDeclaredField("element");
        elementField.setAccessible(true);      
        String element = (String)elementField.get(nodeObject);

        //grab the previous parameter. 
        Field field = innerClass.getDeclaredField("previous");
        field.setAccessible(true);
        Object previous = (Object)field.get(nodeObject);

        // access the getPrevious method to use for asserting if the setPrevious was correct. 
        Method getPrevious= innerClass.getDeclaredMethod("getPrevious");
        getPrevious.setAccessible(true); 

        //assign the getPrevious method to "setPrevious"
        Method setPrevious = innerClass.getDeclaredMethod("setPrevious",innerClass);
        setPrevious.setAccessible(true); 

        // call the private setPrevious method. 
        setPrevious.invoke(nextNodeObject,nodeObject); 
        // assert if the previous node is the same as the one returned from the getPrevious Method. 
        assertEquals(previous,getPrevious.invoke(nodeObject)); 
    }

}
