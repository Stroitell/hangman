import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main{

    private static Scanner scanner = new Scanner(System.in);
    private static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static String reset = "\u001B[0m";
    private static String red = "\u001B[31m";
    private static String green = "\u001B[32m";
    private static String yellow = "\u001B[93m";

    public static void main(String[] args) throws FileNotFoundException {
        startGameLoop();
    }

    private static void startGameLoop () throws FileNotFoundException{

        System.out.println("Начать игру?");
        System.out.println("да/нет");

        String input;
        do {
            input = scanner.nextLine().toLowerCase();
            if (input.equals("да")){
                startIfYes();
            }   else if(input.equals("нет")){
                break;
            }
            else {
                System.out.println("Некорректный ввод");
            }
        }   while (!input.equals("нет"));
    }

    private static void startIfAnotherAnswer(){
        System.out.println("Неккоректный ответ");
    }

    private static void startIfYes () throws FileNotFoundException {

            System.out.println("Допустимые сивмолы: " + green + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + reset);

            String word = makeWord();
            StringBuilder shadow = makeShadow(word);
            List<String> letters = new ArrayList<>();
            int hp = 6;

            //System.out.println(word);  //     <------------------------ отображает загадонное слово в начале игры

            while (hp > 0 & shadow.toString().contains("_")){

                gallowsArts(hp, shadow, word);
                System.out.println("Уже введенные буквы: " + green + String.join(", ", letters) + reset);
                System.out.print("Введите букву: ");

                String character = scanner.nextLine().toLowerCase();

                if (checkValidInput(character)){
                    System.out.println("");
                    System.out.println(red + "Ответ может содержать только одну букву русского алфавита" + reset);
                    System.out.println("Допустимые сивмолы: " + green + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +reset);
                    continue;
                }

                if(letters.contains(character)){
                    System.out.println(yellow +"""
                            
                            Вы уже вводили эту букву.
                            Попробуйте ввести другую.""" + reset);
                    continue;
                }
                addAnwerToList(character, letters);

                if (checkCharacter(word, character) == 1){
                    System.out.println(" ");
                    addCharacterToShadow(character, shadow, word);
                }

                else if (checkCharacter(word, character) == -1 & hp > 1){
                    hp -=1;
                    System.out.println(" ");
                    System.out.println(yellow + "Такой буквы нет. " + reset);
                }
                else if (checkCharacter(word, character) == -1 & hp == 1){
                    hp -=1;
                    gallowsArts(hp, shadow, word);
                }
            }
            if (!shadow.toString().contains("_")){
                System.out.println(green + """
                        
                        Вы ПОБЕДИЛИ!!!
                        
                        """  + reset);
                System.out.println("""
                        Сыграть ещё?
                          да/нет
                        """);
            }
    }

    private  static boolean checkValidStartGameInput(String input){
        if (!input.toLowerCase().equals("да") && !input.toLowerCase().equals("нет")){
            return true;
        }   else {
            return false;
        }
    }

    private static boolean checkValidInput(String character){
        if (!alphabet.contains(character) || !(character.length() == 1)){
            return true;
        }   else{
            return false;
        }
    }

    private static String makeWord () throws FileNotFoundException {

        File file = new File("src/dictionary.txt");
        Scanner scan = new Scanner(file);
        List<String> list = new ArrayList<>();

        while (scan.hasNextLine()){
            list.add(scan.nextLine().toString());
        }

        Random random = new Random();
        String word = list.get(random.nextInt(list.size()));

        scan.close();
        return word;
    }

    private static StringBuilder makeShadow (String word) throws FileNotFoundException{
        StringBuilder shadow = new StringBuilder("_".repeat(word.length()));
        return shadow;
    }

    private static Integer checkCharacter(String word, String character){
        if (word.contains(character)){
            return 1;
        }  else {
            return -1;
        }
    }

    private static StringBuilder addCharacterToShadow(String character, StringBuilder shadow, String word){
        char symbol = character.charAt(0);

        for (int inx = 0; inx < word.length(); inx ++){
            if(word.toCharArray()[inx] == symbol){
                shadow.setCharAt(inx, symbol);
            }
        }
        return shadow;
    }

    private static List<String> addAnwerToList(String character, List<String> letters){
        letters.add(character);
        return letters;
    }

    private static void gallowsArts(int hp, StringBuilder shadow, String word){
        if (hp == 6){
            System.out.println("""
                    _________
                    |       |
                    |       
                    |      
                    |      
                    |     
                    |
                    |
                    |_________
                    """);
            System.out.println(green + shadow + reset);
        }
        if (hp == 5){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |      
                    |      
                    |    
                    |
                    |
                    |_________
                    """);
            System.out.println(green + shadow + reset);
        }        if (hp == 4){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |       |
                    |      
                    |    
                    |
                    |
                    |_________
                    """);
            System.out.println(green + shadow + reset);
        }        if (hp == 3){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |       |
                    |      / 
                    |    
                    |
                    |
                    |_________
                    """);
            System.out.println(green + shadow + reset);
        }        if (hp == 2){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |       |
                    |      / \\
                    |    
                    |
                    |
                    |_________
                    """);
            System.out.println(green + shadow + reset);
        }        if (hp == 1){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |      /|
                    |      / \\
                    |     
                    |
                    |
                    |_________
                    """);
            System.out.println(green + shadow + reset);
        }        if (hp == 0){
            System.out.println("""
                    _________
                    |       |
                    |       O
                    |      /|\\
                    |      / \\
                    |     
                    |
                    |
                    |_________
                    """);
            System.out.println(yellow + "Вы ПРОИГРАЛИ!" + reset);
            System.out.println(yellow + shadow + reset + " --> " + green + word + reset);
            System.out.println("""
                        
                        Играть ещё раз?
                            да/нет""");
        }
    }

}