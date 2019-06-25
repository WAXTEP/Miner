package game;

class Main
{
    //VARIABLES
    private static Player player;
    private static Game game;

    public static void main(String[] args)
    {
        System.out.println("<< Классический сапёр >> \n");

        System.out.println("Выберите режим(введите цифру):");
        System.out.println("1.Лёгкий - 8х8, 8 бомб");
        System.out.println("2.Средний - 16х16, 20 бомб");
        System.out.println("3.Тяжелый - 26х26, 60 бомб \n");

        player = new Player();
        player.readAnswer("1", "2", "3");

        switch (player.getAnswer())
        {
            case "1": game = new Game(8, 8);   break;
            case "2": game = new Game(16, 20); break;
            case "3": game = new Game(26, 60); break;
        }

        System.out.println();
        System.out.println("Для того, чтобы сделать ход, напишите в консоли тип хода(\"flag\" или \"open\"), а затем \n" +
                           "впишите координату вашего хода. Например, сообщения в консоли могут быть такими:         \n" +
                           "Введите тип хода:                                                                        \n" +
                           "flag                                                                                     \n" +
                           "Введите координату:                                                                      \n" +
                           "C6                                                                                       \n");

        System.out.println("Введите что-либо для начала игры:");
        player.readAnything();

        while (true)
        {
            game.printField();

            System.out.println("Введите тип хода:");
            player.readAnswer("FLAG", "Flag", "flag", "OPEN", "Open", "open");

            System.out.println("Введите координату:");
            player.readCoordinate(game.getFieldShape());

            if (player.getAnswer().equals("FLAG"))
            {
                game.flag(player.getCoordinate());
            }
            else if (player.getAnswer().equals("OPEN"))
            {
                game.open(player.getCoordinate());
            }
        }
    }
}
