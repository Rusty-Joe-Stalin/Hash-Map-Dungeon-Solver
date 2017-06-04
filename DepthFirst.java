/**
 * Depth First class 
 * <pre>
 * Course: ADEV -3001
 * Assignment:  #2
 * Date Created: 03/06/2017
 * 
 * Revison Log
 * Who                 When           Reason 
 * ------------------  -------------  ----------------
 * kyle                 apr 15/2017        modified the method for assingment 4 
 *</pre> 
 * @author Kyle Cherewyk
 * @version 1.00 
 */
public class DepthFirst
{   
    private int row , column ;
    private int startRow , startCol;
    private MazePoint[][] maze;
    private MazePoint p;
    private MazePoint mp;   
    private Stack<MazePoint> stack = new Stack<MazePoint>();; 
    private final char visited = 'V';
    private final char exit = 'E';
    private final char hall = ' ';
    private LinkedList<String> QuestInventory  = new LinkedList<>();
    private HashMap<Item, ItemValue> acceptedItems;
    private boolean trapped = false; 
    
    /**
     * Constructor for objects of class DepthFirst
     * 
     */
    public DepthFirst(MazePoint[][]inputMaze , LinkedList<String> inventory, HashMap<Item , ItemValue> acceptedItems )
    {
      this.maze = inputMaze;
      this.QuestInventory = inventory;
      this.acceptedItems = acceptedItems; 
    }

    /**
     * a recursive method to search for the a single value in a two dimensional array
     * 
     * @param  int row - input starting row from the Main class
     * @param  int column - input starting row from the Main class 
     */
    public void depthFirstSearch(int inRow , int inColumn)
    {
       this.row = inRow;
       this.column = inColumn;       
       // if current coordinates are the exit then push the poin to the stack and 
       // print the path then print the maze. 
       if(maze[row][column].isExit()){
          stack.push(maze[row][column]);
          printPath(); 
          printMaze();
          printItems();
       }else if(maze[row][column].canBeNavigated()){
           // mark the current coordinates as visited.           
           stack.push(maze[row][column]);
           maze[row][column].markVisited();              
       }
       if (maze[row][column].hasItem()){
           // pick up the item and create a new Item object. 
           String widget = maze[row][column].pickUpItem();
           Item found = new Item(widget);
           // check the item in the hashmap. 
           if (acceptedItems.get(found)!= null){             
             QuestInventory.add(found.getName()); 
           }else{
               maze[row][column].dropItem(); 
            }
       }       
       if(maze[row][column].wasVisited()){
           // If coordinates have not been visited then create a new point, add it 
           // to the stack then push to stack. Then mark the maze coordinate as visited. 
           if(maze[row+1][column].canBeNavigated()|| maze[row+1][column].isExit()){
            row++; 
           }else if(maze[row][column+1].canBeNavigated()||maze[row][column+1].isExit()){
            column++; 
           }else if(maze[row][column-1].canBeNavigated()||maze[row][column-1].isExit()){
             column--; 
           }else if(maze[row-1][column].canBeNavigated()||maze[row-1][column].isExit()){
            row--;
           }else{       
              // If there is a dead end move back one space.If stack is emptyone  then run no exit 
              // method else reassign the row and column to the previous ones stored in the stack.  
              stack.pop();
              if(stack.isEmpty()){
                  trapped = true; 
                  noExit();
                  // the instructions were unclear if the
                  printItems();
              }else{            
                  mp = stack.top(); 
                  row = mp.getRow(); 
                  column = mp.getColumn();                
              }             
           }
           // If stack is empty drop out of the method. 
           if(!stack.isEmpty()){
             depthFirstSearch(row,column); 
           }
       }
    }
   
    /**
     * print the final total of items in the inventory
     */
    private void printItems(){
       int total = 0; 
       int listSize = QuestInventory.getSize();
       if (listSize !=0 ){
       for(int i =0 ;i< listSize ;i++ ){          
          Item search = new Item(QuestInventory.remove()); 
          ItemValue value = acceptedItems.get(search); 
          total += value.getGoldPieces();
          
          int buffer= 15; 
          //calculate the number of spaces between item name and price. 
          buffer = buffer - search.toString().length(); 
          String line = String.format("%s %"+buffer+"s GP",search.toString(), value.toString());
          System.out.println(line);
       }
       System.out.println("             ------"); 
       System.out.println("Total        "+ total+" GP");
     }else{
        // if there are no items in the list print this message 
       System.out.println("No items were found exploring the maze."); 
      }     
    }
    
    /**
     * outputs the maze after it has been searched and exit is not found 
     */
    private void noExit(){
       System.out.println("There is no exit out of this maze.");
       printMaze();        
    }
    
    /**
     * Reorders the path from end to start to be displayed correctly. 
     * assigns correct path marker to path '.'.
     */
    private void printPath(){
        int size = stack.getSize(); 
        Stack<MazePoint> reordered = new Stack<MazePoint>();
        // use the new stack object to reorder the maze path 
        for(int i = 0 ; i< size ;i++ ){
          MazePoint p = stack.pop();          
          maze[p.getRow()][p.getColumn()].markPath();          
          startRow = p.getRow();
          startCol = p.getColumn();
          reordered.push(p);
        }
        // print out the maze message as required. 
        System.out.println("Path to follow from Start ["+startRow+","+startCol+"] to Exit ["+row+","+column+"] - "+size+" steps"); 
        // then print the reordered stack. 
        for(int i = 0 ; i < size ; i++){
          System.out.println(reordered.pop());
        }       
    }
    
    /**
     * prints the maze when called. 
     */
    private void printMaze(){
       for ( int i = 0; i < maze.length; i++){
          for (int x = 0; x < maze[i].length; x++){
             // if there is no exit change the print out of the maze 
              if(trapped && maze[i][x].wasVisited()){
               System.out.print('V');
              }else{
              System.out.print(maze[i][x].getLocationData());
            }
          }
           System.out.print("\n");
       }    
    }
}
