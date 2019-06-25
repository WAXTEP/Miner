package game;

import field.Field;
import field.FieldPrint;
import services.ResultShow;

class Game
{
    private Field field;

    public Game(int fieldShape, int bombsQuantity)
    {
        field = new Field(fieldShape, bombsQuantity);
    }

    public void open(String userCoordinates)
    {
        int Y, X;

        //transforming into machine-readable coordinates
        if (userCoordinates.length() == 2)
        {
            X = userCoordinates.charAt(0) - 'A';
            Y = userCoordinates.charAt(1) - '1';
        }
        else if (userCoordinates.length() == 3)
        {
            X = userCoordinates.charAt(0) - 'A';
            Y = Integer.parseInt(userCoordinates.substring(1, 3)) - 1;
        }
        else
            throw new IllegalArgumentException();

        field.open(Y, X);

        if (field.isFlag(Y, X))
            return;

        if (field.isBomb(Y, X)) //if opened block is bomb, show lose output
        {
            printField();
            ResultShow.lose();
            System.exit(0);
        }

        if (field.getClearBlocksQuantity() == 0) //if there are no numberBlocks left, player wins
        {
            printField();
            ResultShow.win();
            System.exit(0);
        }
    }
    public void flag(String userCoordinates)
    {
        int Y, X;

        //transforming into machine-readable coordinates
        if (userCoordinates.length() == 2)
        {
            X = userCoordinates.charAt(0) - 'A';
            Y = userCoordinates.charAt(1) - '1';
        }
        else if (userCoordinates.length() == 3)
        {
            X = userCoordinates.charAt(0) - 'A';
            Y = Integer.parseInt(userCoordinates.substring(1, 3)) - 1;
        }
        else
            throw new IllegalArgumentException();

        field.flag(Y, X);
    }

    public int getFieldShape() { return field.shape(); }

    public void printField() { FieldPrint.activate(field); }
}