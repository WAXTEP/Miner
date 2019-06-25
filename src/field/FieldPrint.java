package field;

public class FieldPrint
{
    //gap for imitation of dynamic printing
    public static String SHOW_SPACE =
            "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
            "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    public static void activate(Field f)
    {
        System.out.println(SHOW_SPACE);

        //cycle for X axis letter coordinates showing
        for (char c = 'A'; c < 'A' + f.field.length; c++)
            System.out.print(c + " ");
        System.out.println();

        //cycle for printing entire field
        for (int i1 = 0; i1 < f.field.length; i1++) //Y axis
        {
            for (int i2 = 0; i2 < f.field[i1].length; i2++) //X axis
                System.out.print(f.field[i1][i2] + " ");
            System.out.println("~" + (i1 + 1)); //Y axis number coordinates showing
        }

        System.out.println();
    }
}