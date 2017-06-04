/**
 * Point class
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
public class Point {
    private int row;
    private int parentRow;
    private int column; 
    private int parentColumn;

    /**
     * Default Point constructor
     */
    public Point(int row, int column) {       
        this(row,column,0,0); 
    }

    /**
     * Default Point constructor
     */
    public Point(int row, int column, int parentRow , int parentColumn) {
        this.row = row;
        this.column = column;
        this.parentRow = parentRow;
        this.parentColumn = parentColumn;
    }

    /**
     * Returns value of row
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns value of column
     * @return column 
     */
    public int getColumn() {
        return column;
    }
    
    /**
    * return the parent row when called.
    *@return parentRow
    */
    public int getParentRow(){
      return parentRow;
    }

    /**
    * sets the parent row when called.
    * @param int - parent row 
    **/
    public void setParentRow(int parentRow){
      this.parentRow = parentRow;
    }

    /**
    *return the parent column when called
    *@return parentColumn
    */
    public int getParentColumn(){
      return parentColumn;
    }

    /**
    * sets the parent column when called
    * @param int - parent column 
    */
    public void setParentColumn(int parentColumn){
      this.parentColumn = parentColumn;
    }

    /**
     * Create string representation of Point for printing
     * @return string 
     */
    @Override
    public String toString() {
        return "[" + row + ", " + column +"]" ;
    }
}
