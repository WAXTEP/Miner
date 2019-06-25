package field;

public class Field
{
    Block[][] field; //refer as [Y][X]; coordinates are going from up to down(Y), from left to right(X).
    private int bombsQuantity, clearBlocks; //clear blocks var - non-bomb blocks quantity

    public Field(int shape, int bombsQuantity)
    {
        //Input data validation
        if (bombsQuantity < 1 || shape < 1 || shape > 26) //(about 3rd statement):
            throw new IllegalArgumentException();         //there are only 26 letters in english alphabet, so...

        field = new Block[shape][shape];
        this.bombsQuantity = bombsQuantity;
        this.clearBlocks = shape * shape - bombsQuantity;

        //1. Initialising some random blocks with BombBlock{} objects so we can create NumberBlock{} objects later.
        for (; bombsQuantity > 0; bombsQuantity--)
        {
            int Y = (int)(Math.random() * shape), //getting random
                X = (int)(Math.random() * shape); //coordinates

            if (field[Y][X] instanceof BombBlock) //if randomized block is already a bomb,
                bombsQuantity++;                  //compensate cycle bombsQuantity decrement.
            else
                field[Y][X] = new BombBlock();
        }

        //2. Initializing every else non-bomb block as NumberBlock{} object
        for (int i1 = 0; i1 < shape; i1++)
        {
            for (int i2 = 0; i2 < shape; i2++)
            {
                if (field[i1][i2] instanceof BombBlock) //if current block is a bomb, go to the next
                    continue;                           //block without completing further code

                int bombs = 0; //nearby bombs counter

                //try-catch blocks(for ignoring(that's safe, worry not) ArrayIndexOutOfBoundsException)
                //that are counting all nearby bombs so we can create proper NumberBlock{} objects
                tryCatchBlocks: {
                try { if (field[i1 - 1][i2 - 1] instanceof BombBlock) bombs++; }
                catch (ArrayIndexOutOfBoundsException ignored) { }

                try { if (field[i1 - 1][i2] instanceof BombBlock) bombs++; }
                catch (ArrayIndexOutOfBoundsException ignored) { }

                try { if (field[i1 - 1][i2 + 1] instanceof BombBlock) bombs++; }
                catch (ArrayIndexOutOfBoundsException ignored) { }

                try { if (field[i1][i2 - 1] instanceof BombBlock) bombs++; }
                catch (ArrayIndexOutOfBoundsException ignored) { }

                try { if (field[i1][i2 + 1] instanceof BombBlock) bombs++; }
                catch (ArrayIndexOutOfBoundsException ignored) { }

                try { if (field[i1 + 1][i2 - 1] instanceof BombBlock) bombs++; }
                catch (ArrayIndexOutOfBoundsException ignored) { }

                try { if (field[i1 + 1][i2] instanceof BombBlock) bombs++; }
                catch (ArrayIndexOutOfBoundsException ignored) { }

                try { if (field[i1 + 1][i2 + 1] instanceof BombBlock) bombs++; }
                catch (ArrayIndexOutOfBoundsException ignored) { }
                }

                field[i1][i2] = new NumberBlock(bombs);
            }
        }
    }

    //arguments could be invalid and throw ArrayOutOfBoundsException,
    //besides, if we open BombBlock, method throws OpenedBombException(we handle it in the Game{} class)
    public void open(int Y, int X)
    {
        if (!field[Y][X].isOpened())
            clearBlocks--;

        field[Y][X].open();

        if (field[Y][X].getRealVal() != Block.ZERO) //no need to execute further code if current block
            return;                                 //doesn't have value of Block.ZERO


        class JustForMethod //class inside a method for more laconic code
        {
            //we use this method for all blocks that are surrounding current block
            //(modified Field.open() method, actually, that is filtering the recursion)
            void openSurr(int Y, int X)
            {
                try
                {
                    if (!field[Y][X].isOpened()) //no need to open already opened block
                    {
                        if (field[Y][X].getRealVal() != Block.ZERO) //no need to make excess recursion...
                        {                                           //besides, program won't compile if I actually make it :)
                            field[Y][X].open();
                            clearBlocks--;
                        }
                        else                                        //if this surrounding block equals zero,
                            open(Y, X);                             //open it recursively
                    }
                }
                catch (ArrayIndexOutOfBoundsException ignored) { }
            }
        }
        JustForMethod inside = new JustForMethod();
        inside.openSurr(Y - 1, X - 1);
        inside.openSurr(Y - 1, X);
        inside.openSurr(Y - 1, X + 1);
        inside.openSurr(Y, X - 1);
        inside.openSurr(Y, X + 1);
        inside.openSurr(Y + 1, X - 1);
        inside.openSurr(Y + 1, X);
        inside.openSurr(Y + 1, X + 1);
    }
    public void flag(int Y, int X) { field[Y][X].flag(); }

    public int getClearBlocksQuantity() { return clearBlocks; }
    public int shape() { return field.length; }

    public boolean isBomb(int Y, int X) { return field[Y][X] instanceof BombBlock; }
    public boolean isFlag(int Y, int X) { return field[Y][X].getSeenVal() == Block.FLAG; }
}