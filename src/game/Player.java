package game;

import java.util.Scanner;

class Player
{
    private Scanner read = new Scanner(System.in);
    private String answer;
    private String coordinate;

    public void readAnswer(String ... validAnswers)
    {
        if (validAnswers.length == 0)
            throw new IllegalArgumentException();

        answer = read.nextLine();

        outerCycle:
        while(true)
        {
            for (String s : validAnswers)
                if (answer.equals(s))
                    break outerCycle;

            System.out.println("Неправильно введённые данные. Повторите вашу попытку");
            answer = read.nextLine();
        }
    }
    public void readCoordinate(int fieldShape)
    {
        if (fieldShape < 1 || fieldShape > 26)
            throw new IllegalArgumentException();

        coordinate = read.nextLine();
        while (true)
        {
            try
            {
                if ((coordinate.length() != 2 && coordinate.length() != 3) ||
                    coordinate.toUpperCase().charAt(0) < 'A' || coordinate.toUpperCase().charAt(0) >= 'A' + fieldShape)
                {
                    throw new IllegalArgumentException();
                }
                else if (coordinate.length() == 2)
                {
                    if (coordinate.charAt(1) < '1' || coordinate.charAt(1) >= '1' + fieldShape)
                        throw new IllegalArgumentException();
                }
                else if (coordinate.length() == 3)
                {
                    if (Integer.parseInt(coordinate.substring(1, 3)) < 10 || Integer.parseInt(coordinate.substring(1, 3)) > fieldShape)
                        throw new IllegalArgumentException();
                }

                return; //if coordinate is valid, exit from the method
            }
            catch (IllegalArgumentException exc)
            {
                System.out.println("Неправильно введённые данные. Повторите вашу попытку");
            }

            coordinate = read.nextLine();
        }
    }
    public void readAnything() { String anything = read.nextLine(); }

    //answers and coordinates are returned in upper case by default settings(I should remember this while using these methods)
    public String getAnswer() { return answer.toUpperCase().trim(); }
    public String getCoordinate() { return coordinate.toUpperCase().trim(); }
}