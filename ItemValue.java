
/**
 * ItemValue
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
 * @version 1.00
 * 
 */
public class ItemValue
{
    private int goldPieces;
    /**
     * Constructor for objects of class ItemValue
     */
    public ItemValue(int goldPieces)
    {
        this.goldPieces = goldPieces; 
    }
    
    /**
     * returns the number of gold pieces of an item 
     * @return  int goldPeices
     */
    public int getGoldPieces()
    {
       return goldPieces; 
    }
    
    /**
     * returns number of gold Peices in a string format. 
     * @return string 
     */
    public String toString(){
        return String.valueOf(goldPieces); 
    }
}
