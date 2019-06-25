package field;

abstract class Block
{
    public final static char HIDDEN = '_', FLAG = 'P',                       //closed blocks values
                             BOMB = 'Q', ZERO = ' ', ONE = '1', TWO = '2',
                             THREE = '3', FOUR = '4', FIVE = '5', SIX = '6'; //opened block values

    char realVal, seenVal = HIDDEN;
    boolean isOpened = false;

    Block(int realVal) //realVal = -1; -> realVal - bomb
    {
        switch(realVal)
        {
            case -1: this.realVal = BOMB;   break;
            case  0: this.realVal = ZERO;   break;
            case  1: this.realVal = ONE;    break;
            case  2: this.realVal = TWO;    break;
            case  3: this.realVal = THREE;  break;
            case  4: this.realVal = FOUR;   break;
            case  5: this.realVal = FIVE;   break;
            case  6: this.realVal = SIX;    break;
            default: throw new IllegalArgumentException();//if val > 6, game almost always becomes impossible to win
        }
    }

    @Override
    public String toString() { return seenVal + ""; }

    public char getRealVal() { return realVal; }
    public char getSeenVal() { return seenVal; }
    public boolean isOpened() { return isOpened; }

    public void flag()
    {
        if (isOpened)
            return;
        else
            seenVal = FLAG;
    }
    public void open()
    {
        seenVal = realVal;
        isOpened = true;
    }
}