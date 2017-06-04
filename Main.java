import java.util.Iterator;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
/**
 * Main
 * Assignment 4
 * Course ADEV -3001
 * Date Created: 04/11/2017
 * <pre>
 * Revison Log 
 * Who              When            Reason 
 * ------------     -------------   --------------
 * kyle              04/12/2017     need modifacations for Assignment 4 final 
 * </pre> 
 * @author Kyle Cherewyk
 * @version 1.03
 * 
 */
public class Main
{
    @SuppressWarnings("unchecked")
    public static void main( String[] args )throws IOException
    {
          String file = "ItemData.txt";
          Path filePath = Paths.get(file);
          List<String> list  = Files.readAllLines(filePath);
          String[] parts;
          
          HashMap<Item,ItemValue> hashieMazeie = new HashMap<>(5);
          for(int i =0; i< list.size();i++)
          {
            parts = list.get(i).split(" ");
             if(parts[0] != null && parts[1] != null){                            
              Item name = new Item(parts[0]); 
              ItemValue cash = new ItemValue(Integer.parseInt(parts[1])); 
              hashieMazeie.put(name, cash);
              // output the maze as it is insert for testing
              //System.out.println(parts[0] + ": " + parts[1]);
            }
          }
          
          Iterator loopie = hashieMazeie.keys();
          ItemValue goldieGold = null; 
          Item questItem = null;
          // loop through entries to filter out items with no value 
          while(loopie.hasNext()){              
             questItem = (Item)loopie.next();
             goldieGold = hashieMazeie.get(questItem);           
             if(goldieGold.getGoldPieces()==0 )
             {
                 hashieMazeie.remove(questItem);
             }
          }    
          
         // Final Assignment processing ----------------------------------------
         MazePoint[][] maze = Maze.getMaze();
         int[] startingPoint = Maze.getStartCoordinates(); 
         int startRow = startingPoint[0]; 
         int startCol = startingPoint[1];
         
         LinkedList<String> inventory = new LinkedList<>();         
         DepthFirst search = new DepthFirst(maze , inventory , hashieMazeie );
         search.depthFirstSearch(startRow , startCol);

    }
}
