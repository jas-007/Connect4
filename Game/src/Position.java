// CLASS: Position
//
// Author: Jaspreet Singh, 7846401
//
// REMARKS: The purpose is to store position as row and column.
//
//-----------------------------------------
public class Position {


    private int row;
    private int column;
    public Position()
    {
        row=0;
        column=0;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
}